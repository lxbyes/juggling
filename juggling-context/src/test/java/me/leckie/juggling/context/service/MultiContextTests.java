package me.leckie.juggling.context.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * @author Leckie
 * @version $Id: MultiContextTests.java, v0.1 2018/11/15 14:24 Leckie Exp $$
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
      this.subApplicationContext = newSubApplicationContext();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }


  /**
   * BeanFactoryAware inject the applicationContext.getBeanFactory()
   */
  @Test
  public void testBeanFactoryWithApplicationContextEqual() {
    Assert.assertTrue(applicationContext.getBeanFactory() == beanFactory);
  }

  @Test
  public void testRegistryOnSubContext() {
    String beanName = "me.leckie.juggling.simple.AService";
    registerBeanOn(subApplicationContext, beanName);
    Assert.assertFalse(applicationContext.containsBean(beanName));
    Assert.assertTrue(subApplicationContext.containsBean(beanName));
    AInterface aInterface = (AInterface) subApplicationContext.getDefaultListableBeanFactory().getBean(beanName);
    System.out.println(aInterface.a("xiexie"));
  }

  @Test
  public void testRegistrySetOnSubContext() {
    String[] beanNames = {"me.leckie.juggling.simple.AService", "me.leckie.juggling.context.service.AAAService"};
    registerBeanOn(subApplicationContext, beanNames);
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
    registerBeanOn(subApplicationContext, beanNames);
    Map<String, AInterface> beansOfType = subApplicationContext.getBeansOfType(AInterface.class);
    beansOfType.putAll(subApplicationContext.getParent().getBeansOfType(AInterface.class));
    List<AInterface> aInterfaceList = new ArrayList<>(beansOfType.values());
    aInterfaceList.sort(AInterfaceComparator.getInstance());
    System.out.println(aInterfaceList);
  }

  /**
   * localBean
   */
  @Test
  public void testContainsLocalBean() {
    String beanName = "ABService";
    Assert.assertTrue(subApplicationContext.getParent().containsLocalBean(beanName));
    Assert.assertFalse(subApplicationContext.containsLocalBean(beanName));
    Assert.assertTrue(subApplicationContext.getParent().containsBean(beanName));
    Assert.assertTrue(subApplicationContext.containsBean(beanName));
  }

  /**
   * register bean on parent container, sub can get the bean by name, not by type
   */
  @Test
  public void testRegistryOnParentContext() {
    String beanName = "me.leckie.juggling.context.service.AAAService";
    registerBeanOn(applicationContext, beanName);
    Assert.assertTrue(applicationContext.containsBean(beanName));
    Assert.assertTrue(subApplicationContext.containsBean(beanName));
    AInterface aInterface = (AInterface) applicationContext.getBean(beanName);
    Assert.assertTrue(aInterface == subApplicationContext.getBean(beanName));
    System.out.println(aInterface.a("xiexie"));
  }

  /**
   * multi sub context isolation
   */
  @Test
  public void testMultiSubContext() throws MalformedURLException {
    GenericApplicationContext anotherContext = newSubApplicationContext();
    String[] beanNames = {"me.leckie.juggling.simple.AService", "me.leckie.juggling.context.service.AAAService"};
    registerBeanOn(anotherContext, beanNames);
    registerBeanOn(subApplicationContext, beanNames);
    Assert.assertTrue(anotherContext.getBean(beanNames[0]) != subApplicationContext.getBean(beanNames[0]));
    Assert.assertTrue(anotherContext.getBean("ABService") == subApplicationContext.getBean("ABService"));
  }

  @Test
  public void testCloseApplicationContext() throws IOException {
    GenericApplicationContext anotherContext = newSubApplicationContext();
    String[] beanNames = {"me.leckie.juggling.simple.AService", "me.leckie.juggling.context.service.AAAService"};
    registerBeanOn(anotherContext, beanNames);
    Assert.assertTrue(anotherContext.isActive());
    Assert.assertTrue(anotherContext.isRunning());
    anotherContext.close();
    Assert.assertFalse(anotherContext.isActive());
    Assert.assertFalse(anotherContext.isRunning());
  }

  @Test
  public void testSupContextBeanWithUtils()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    String beanName = "me.leckie.juggling.simple.FooService";
    registerBeanOn(subApplicationContext, beanName);
    Object bean = subApplicationContext.getBean(beanName);
    Method bar = bean.getClass().getMethod("bar");
    Method println = bean.getClass().getMethod("println");
    bar.invoke(bean);
    println.invoke(bean);
  }

  @Test
  public void testScanPackages() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = (AnnotationConfigApplicationContext) subApplicationContext;
    annotationConfigApplicationContext.scan("me.leckie.juggling");
    String beanName = "fooService";
    Object bean = annotationConfigApplicationContext.getBean(beanName);
    Method bar = bean.getClass().getMethod("bar");
    bar.invoke(bean);
    annotationConfigApplicationContext.getBeansOfType(Object.class).keySet().forEach(System.out::println);
  }

  @Test(expected = NoSuchBeanDefinitionException.class)
  public void testScanPackagesNoSuchBean()
      throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = (AnnotationConfigApplicationContext) subApplicationContext;
    annotationConfigApplicationContext.scan("no.this.package");
    String beanName = "fooService";
    Object bean = subApplicationContext.getBean(beanName);
    Method bar = bean.getClass().getMethod("bar");
    bar.invoke(bean);
  }

  @Test
  public void testBeanNameGenerator() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = (AnnotationConfigApplicationContext) subApplicationContext;
    annotationConfigApplicationContext.scan("me.leckie.juggling");
    annotationConfigApplicationContext.getBeansOfType(Object.class).keySet().forEach(
        key -> System.out.println(key + ": " + annotationConfigApplicationContext.getBean(key).getClass().getName()));
  }


  /**
   * parent and child container can have same beanName
   */
  @Test
  public void testParentChildNotSameNameBean() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = (AnnotationConfigApplicationContext) subApplicationContext;
    annotationConfigApplicationContext.scan("me.leckie");
    String beanName = "ABService";
    Assert.assertFalse(applicationContext.getBean(beanName) == annotationConfigApplicationContext.getBean(beanName));
  }

  @Test
  public void testParentChildSameNameBean() {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = (AnnotationConfigApplicationContext) subApplicationContext;
    annotationConfigApplicationContext.scan("me.leckie.juggling.simple");
    String beanName = "ABService";
    Assert.assertTrue(applicationContext.getBean(beanName) == annotationConfigApplicationContext.getBean(beanName));
  }

  private GenericApplicationContext newSubApplicationContext() throws MalformedURLException {
    AnnotationConfigApplicationContext subApplicationContext = new AnnotationConfigApplicationContext();
    subApplicationContext.setParent(applicationContext);
    URLClassLoader urlClassLoader = URLClassLoader.newInstance(
        new URL[]{new URL("file:\\D:\\juggling-simple-2.jar"), new URL("file:\\D:\\juggling-simple-1.jar")});
    subApplicationContext.setClassLoader(urlClassLoader);
    subApplicationContext.setAllowBeanDefinitionOverriding(true);
    subApplicationContext.refresh();
    subApplicationContext.setBeanNameGenerator(new AnnotationBeanNameGenerator());
    subApplicationContext.start();
    return subApplicationContext;
  }

  private static void registerBeanOn(BeanDefinitionRegistry beanDefinitionRegistry, String... beanNames) {
    for (String beanName : beanNames) {
      beanDefinitionRegistry.registerBeanDefinition(beanName,
          BeanDefinitionBuilder.genericBeanDefinition(beanName).getBeanDefinition());
    }
  }
}