package me.leckie.dynamicbeanload.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class DynamicbeanloadService implements ApplicationContextAware {

  @Autowired
  private DynamicService dynamicService;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
    DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableApplicationContext
        .getBeanFactory();
    String beanName = "dynamicService";
    beanFactory.removeBeanDefinition(beanName);
    beanFactory
        .registerBeanDefinition(beanName,
            BeanDefinitionBuilder.genericBeanDefinition(DynamicService.class.getName()).getBeanDefinition());
    // 之前的对象依然被引用
    System.out.println(dynamicService);
    System.out.println(applicationContext.containsBean(beanName));
    URLClassLoader urlClassLoader = new URLClassLoader(new URL[100]);
    try {
      urlClassLoader.getURLs()[0] = new URL("http://asdsdfa.com");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    beanFactory.setTempClassLoader(urlClassLoader);
    beanFactory.setBeanClassLoader(urlClassLoader);
    beanFactory.setCacheBeanMetadata(false);
    beanFactory.clearMetadataCache();
    beanFactory.setAllowBeanDefinitionOverriding(true);
    System.out.println(beanFactory.getBeanClassLoader().getClass().getName());
    System.out.println(beanFactory.getBeanClassLoader().getParent().getClass().getName());
    // 获取bean时重新被初始化
    DynamicService dynamicServiceNew = applicationContext.getBean(DynamicService.class);
    System.out.println(dynamicServiceNew);
    System.out.println(dynamicService == dynamicServiceNew); //false
    System.out.println(dynamicServiceNew.getClass().getClassLoader().getClass().getName());
  }
}
