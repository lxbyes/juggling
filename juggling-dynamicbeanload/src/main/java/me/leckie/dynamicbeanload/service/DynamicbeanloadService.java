package me.leckie.dynamicbeanload.service;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import me.leckie.juggling.facade.AInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class DynamicbeanloadService implements ApplicationContextAware {

  private Logger logger = LoggerFactory.getLogger(DynamicbeanloadService.class);

  @Autowired
  private List<AInterface> aInterfaces;

  @Autowired
  private DynamicService dynamicService;

  private ApplicationContext applicationContext;

  private DefaultListableBeanFactory beanFactory;

  public List<Object> listBeans() {
    String[] beanNames = applicationContext.getBeanNamesForType(Object.class);
    List<Object> list = new ArrayList<>(beanNames.length);
    for (String beanName : beanNames) {
      Map<String, Object> item = new HashMap<>();
      item.put("beanName", beanName);
      item.put("bean", beanFactory.getBean(beanName).getClass().getName());
      if (beanFactory.containsBeanDefinition(beanName)) {
        item.put("beanDefine", beanFactory.getBeanDefinition(beanName).getClass().getName());
      }
      list.add(item);
    }
    return list;
  }

  public void addBean(String beanName) {
    if (beanFactory.containsBeanDefinition(beanName)) {
      beanFactory.removeBeanDefinition(beanName);
    }
    beanFactory
        .registerBeanDefinition(beanName, BeanDefinitionBuilder.genericBeanDefinition(beanName).getBeanDefinition());
  }

  public void updateBean(String beanName) {
    try {
      URLClassLoader urlClassLoader = URLClassLoader
          .newInstance(new URL[]{new URL("file:\\D:\\juggling-simple-5.jar")});
      beanFactory.setBeanClassLoader(urlClassLoader);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }
    addBean(beanName);
  }

  public void deleteBean(String beanName) {
    if (beanFactory.containsBeanDefinition(beanName)) {
      beanFactory.removeBeanDefinition(beanName);
    }
  }

  public Object getBean(String beanName) {
    if (beanFactory.containsBeanDefinition(beanName)) {
      Map<String, Object> item = new HashMap<>();
      item.put("beanName", beanName);
      item.put("bean", beanFactory.getBean(beanName).getClass().getName());
      if (beanFactory.containsBeanDefinition(beanName)) {
        item.put("beanDefine", beanFactory.getBeanDefinition(beanName).getClass().getName());
      }
      Object bean = beanFactory.getBean(beanName);
      Class<?> beanClass = bean.getClass();
      Arrays.stream(beanClass.getMethods()).forEach(method -> {
            method.setAccessible(true);
            try {
              if (method.getName().equals("say")) {
                method.invoke(bean);
              } else if (method.getName().equals("b")) {
                System.out.println(method.invoke(bean, "b"));
              } else if (method.getName().equals("a")) {
                System.out.println(method.invoke(bean, "a"));
              }
            } catch (IllegalAccessException e) {
              e.printStackTrace();
            } catch (InvocationTargetException e) {
              e.printStackTrace();
            }
          }
      );
      return item;
    }
    return null;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
    this.applicationContext = applicationContext;
    this.beanFactory = (DefaultListableBeanFactory) configurableApplicationContext
        .getBeanFactory();
    try {
      // 使用URLClassLoader
      URLClassLoader urlClassLoader = URLClassLoader
          .newInstance(new URL[]{new URL("file:\\D:\\juggling-simple-1.jar")});
      beanFactory.setBeanClassLoader(urlClassLoader);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      logger.error(e.getMessage(), e);
      throw new RuntimeException(e);
    }

    /*String beanName = "dynamicService";
    beanFactory.removeBeanDefinition(beanName);
    beanFactory
        .registerBeanDefinition(beanName,
            BeanDefinitionBuilder.genericBeanDefinition(DynamicService.class.getName()).getBeanDefinition());
    // 之前的对象依然被引用
    System.out.println(dynamicService);
    System.out.println(applicationContext.containsBean(beanName));
    URLClassLoader urlClassLoader = null;
    try {
      urlClassLoader = new URLClassLoader(new URL[]{new URL("file:\\D:\\juggling-simple.jar")});
    } catch (MalformedURLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    beanFactory.setTempClassLoader(urlClassLoader);
    beanFactory.setBeanClassLoader(urlClassLoader);
    System.out.println(beanFactory.getBeanClassLoader().getClass().getName());
    System.out.println(beanFactory.getBeanClassLoader().getParent().getClass().getName());
    // 获取bean时重新被初始化
    DynamicService dynamicServiceNew = applicationContext.getBean(DynamicService.class);
    System.out.println(dynamicServiceNew);
    System.out.println(dynamicService == dynamicServiceNew); //false
    System.out.println(dynamicServiceNew.getClass().getClassLoader().getClass().getName());
    // 先后顺序不要紧
    beanFactory.registerBeanDefinition("barService",
        BeanDefinitionBuilder.genericBeanDefinition("me.leckie.juggling.simple.BarService").getBeanDefinition());
    beanFactory.registerBeanDefinition("fooService",
        BeanDefinitionBuilder.genericBeanDefinition("me.leckie.juggling.simple.FooService").getBeanDefinition());
    Object fooService = beanFactory.getBean("barService");
    Class<?> fooServiceClass = fooService.getClass();
    try {
      Method say = fooServiceClass.getMethod("say");
      say.invoke(fooService);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }*/
  }

  public Object getAService() {
    return Arrays.stream(beanFactory.getBeanNamesForType(AInterface.class)).map(beanName -> {
      System.out.println(beanName + " containsBean: " + beanFactory.containsBean(beanName));
      return beanFactory.getBean(beanName).getClass().getName();
    }).collect(Collectors.toList());
  }
}
