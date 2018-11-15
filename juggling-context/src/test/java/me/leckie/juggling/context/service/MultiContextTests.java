package me.leckie.juggling.context.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import me.leckie.juggling.context.comparator.AInterfaceComparator;
import me.leckie.juggling.facade.AInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * @author Leckie
 * @version $Id: MultiContextTests.java, v0.1 2018/11/15 14:24 Leckie Exp $$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MultiContextTests implements BeanFactoryAware, ApplicationContextAware {

  private GenericWebApplicationContext applicationContext;

  private DefaultListableBeanFactory beanFactory;

  private GenericApplicationContext subApplicationContext;

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    this.beanFactory = (DefaultListableBeanFactory) beanFactory;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = (GenericWebApplicationContext) applicationContext;
    try {
      this.subApplicationContext = makeNewSubApplicationContext();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testBeanFactoryWithApplicationContextEqual() {
    Assert.assertTrue(applicationContext.getBeanFactory() == beanFactory);
  }

  @Test
  public void testRegistryOnSubContext() {
    String beanName = "me.leckie.juggling.simple.AService";
    registerBeanOnSubRegistry(beanName);
    Assert.assertFalse(applicationContext.containsBean(beanName));
    Assert.assertTrue(subApplicationContext.containsBean(beanName));
    AInterface aInterface = (AInterface) subApplicationContext.getDefaultListableBeanFactory().getBean(beanName);
    System.out.println(aInterface.a("xiexie"));
  }

  @Test
  public void testRegistrySetOnSubContext() {
    String[] beanNames = {"me.leckie.juggling.simple.AService", "me.leckie.juggling.context.service.AAAService"};
    registerBeanOnSubRegistry(beanNames);
    Map<String, AInterface> beansOfType = subApplicationContext.getBeansOfType(AInterface.class);
    Assert.assertEquals(beansOfType.size(), 2);
    Assert.assertTrue(beansOfType.containsKey(beanNames[0]));
    Assert.assertTrue(beansOfType.containsKey(beanNames[1]));
    beansOfType.forEach((k, v) -> System.out.println(v.a(k)));
    System.out.println(subApplicationContext.getParent().getBeansOfType(AInterface.class));
  }

  @Test
  public void testOrder() {
    String[] beanNames = {"me.leckie.juggling.simple.AService", "me.leckie.juggling.context.service.AAAService"};
    registerBeanOnSubRegistry(beanNames);
    Map<String, AInterface> beansOfType = subApplicationContext.getBeansOfType(AInterface.class);
    beansOfType.putAll(subApplicationContext.getParent().getBeansOfType(AInterface.class));
    List<AInterface> aInterfaceList = new ArrayList<>(beansOfType.values());
    aInterfaceList.sort(AInterfaceComparator.getInstance());
    System.out.println(aInterfaceList);
  }

  @Test
  public void testContainsLocalBean() {
    String beanName = "ABService";
    Assert.assertTrue(subApplicationContext.getParent().containsLocalBean(beanName));
    Assert.assertFalse(subApplicationContext.containsLocalBean(beanName));
    Assert.assertTrue(subApplicationContext.getParent().containsBean(beanName));
    Assert.assertTrue(subApplicationContext.containsBean(beanName));
  }

  @Test
  public void testRegistryOnParentContext() {
    String beanName = "me.leckie.juggling.context.service.AAAService";
    registerBeanOnRegistry(beanName);
    Assert.assertTrue(applicationContext.containsBean(beanName));
    Assert.assertTrue(subApplicationContext.containsBean(beanName));
    AInterface aInterface = (AInterface) applicationContext.getBean(beanName);
    Assert.assertTrue(aInterface == subApplicationContext.getBean(beanName));
    System.out.println(aInterface.a("xiexie"));
  }

  private GenericApplicationContext makeNewSubApplicationContext() throws MalformedURLException {
    GenericApplicationContext subApplicationContext = new AnnotationConfigApplicationContext();
    subApplicationContext.setParent(applicationContext);
    URLClassLoader urlClassLoader = URLClassLoader.newInstance(
        new URL[]{new URL("file:\\D:\\juggling-simple-2.jar"), new URL("file:\\D:\\juggling-simple-1.jar")});
    subApplicationContext.setClassLoader(urlClassLoader);
    subApplicationContext.setAllowBeanDefinitionOverriding(true);
    subApplicationContext.refresh();
    subApplicationContext.start();
    return subApplicationContext;
  }

  private void registerBeanOnRegistry(String... beanNames) {
    for (String beanName : beanNames) {
      applicationContext.registerBeanDefinition(beanName,
          BeanDefinitionBuilder.genericBeanDefinition(beanName).getBeanDefinition());
    }
  }

  private void registerBeanOnSubRegistry(String... beanNames) {
    for (String beanName : beanNames) {
      subApplicationContext.registerBeanDefinition(beanName,
          BeanDefinitionBuilder.genericBeanDefinition(beanName).getBeanDefinition());
    }
  }
}