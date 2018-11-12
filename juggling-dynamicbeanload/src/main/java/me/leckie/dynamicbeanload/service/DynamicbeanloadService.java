package me.leckie.dynamicbeanload.service;

import org.springframework.beans.BeansException;
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
    // 获取bean时重新被初始化
    DynamicService dynamicServiceNew = applicationContext.getBean(DynamicService.class);
    System.out.println(dynamicServiceNew);
    System.out.println(dynamicService == dynamicServiceNew); //false

  }
}
