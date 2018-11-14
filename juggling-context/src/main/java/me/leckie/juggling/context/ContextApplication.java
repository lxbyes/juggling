package me.leckie.juggling.context;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author laixianbo
 * @version $Id: ContextApplication.java, v0.1 2018/11/14 14:42 laixianbo Exp $$
 */
@SpringBootApplication
public class ContextApplication {

  public static void main(String[] args) throws MalformedURLException {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(ContextApplication.class, args);
    DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
    GenericApplicationContext subApplicationContext = new GenericApplicationContext();
    DefaultListableBeanFactory subBeanFactory = (DefaultListableBeanFactory) subApplicationContext.getBeanFactory();
    subApplicationContext.setParent(applicationContext);
    URLClassLoader urlClassLoader = URLClassLoader.newInstance(
        new URL[]{new URL("file:\\D:\\juggling-simple-7.jar"), new URL("file:\\D:\\juggling-simple-6.jar")});
    subBeanFactory.setParentBeanFactory(beanFactory);
    beanFactory.setBeanClassLoader(urlClassLoader);
    subBeanFactory.setBeanClassLoader(urlClassLoader);
    subApplicationContext.refresh();
    subApplicationContext.start();
    beanFactory.setAllowBeanDefinitionOverriding(true);
    registerBean(beanFactory, "me.leckie.juggling.simple.FooService", "me.leckie.juggling.simple.FooService",
        "me.leckie.juggling.simple.AService");

    System.out.println("-------------------------");
    Arrays.stream(subApplicationContext.getBeanNamesForType(Object.class)).forEach(System.out::println);
  }

  private static void registerBean(DefaultListableBeanFactory beanFactory, String... beanNames) {
    for (String beanName : beanNames) {
      beanFactory.registerBeanDefinition(beanName,
          BeanDefinitionBuilder.genericBeanDefinition(beanName).getBeanDefinition());
      System.out.println("-------------------------");
      Object bean = beanFactory.getBean(beanName);
      System.out.println(bean.getClass().getName());
    }
  }

}
