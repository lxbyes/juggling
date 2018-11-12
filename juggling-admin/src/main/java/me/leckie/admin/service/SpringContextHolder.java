package me.leckie.admin.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
    DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableApplicationContext
        .getBeanFactory();
    System.out.println(applicationContext.getClass().getName());
    System.out.println(beanFactory.getClass().getName());
    System.out.println(beanFactory instanceof DefaultListableBeanFactory);
    System.out.println(applicationContext instanceof ConfigurableApplicationContext);
  }
}
