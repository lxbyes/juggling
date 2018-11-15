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
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Service
public class DynamicbeanloadService implements ApplicationContextAware {

  private Logger logger = LoggerFactory.getLogger(DynamicbeanloadService.class);

  @Autowired
  private List<AInterface> aInterfaces;

  @Autowired
  private DynamicService dynamicService;

  @Autowired
  private RequestMappingHandlerMapping requestMappingHandlerMapping;


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

  public List<Object> listBeanDefinitions() {
    return Arrays.stream(beanFactory.getBeanDefinitionNames()).map(beanDefinitionName -> {
      Map map = new HashMap();
      map.put("beanDefinitionName", beanDefinitionName);
      BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
      map.put("beanDefinitionClass", beanDefinition.getBeanClassName());
      map.put("DependsOn", beanDefinition.getDependsOn());
      map.put("Description", beanDefinition.getDescription());
      map.put("ParentName", beanDefinition.getParentName());
      map.put("ResourceDescription", beanDefinition.getResourceDescription());
      map.put("FactoryBeanName", beanDefinition.getFactoryBeanName());
      return map;
    }).collect(Collectors.toList());
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
      loadJars(new URL("file:\\D:\\juggling-simple-2.jar"));
    } catch (MalformedURLException e) {
      e.printStackTrace();
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

  public Object registerMapping(String beanName) {
    addBean(beanName);
    Object bean = beanFactory.getBean(beanName);
    Map item = new HashMap();
    item.put("beanName", beanName);
    item.put("beanClass", bean.getClass().getName());
    item.put("mappingMethodInfos", Arrays.stream(bean.getClass().getMethods())
        .filter(
            method -> Arrays.stream(method.getAnnotations())
                .anyMatch(
                    annotation -> annotation.annotationType().isAnnotationPresent(RequestMapping.class) || annotation
                        .annotationType().equals(RequestMapping.class)))
        .map(method -> {
          Map<String, Object> info = new HashMap<>();
          RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
          if (requestMapping != null) {
            info.put("pattern", requestMapping.value());
            if (requestMapping.method().length > 0) {
              info.put("method", Arrays.stream(requestMapping.method()).map(m -> m.name()).toArray());
            } else {
              info.put("method", Arrays.stream(HttpMethod.values()).map(m -> m.name()).toArray());
            }
            info.put("path", requestMapping.path());
            requestMappingHandlerMapping.registerMapping(RequestMappingInfo.paths(requestMapping.value())
                    .methods(requestMapping.method().length > 0 ? requestMapping.method() : RequestMethod.values()).build(),
                beanName, method);
            return info;
          }
          GetMapping getMapping = method.getAnnotation(GetMapping.class);
          if (getMapping != null) {
            info.put("pattern", getMapping.value());
            info.put("method", HttpMethod.GET.name());
            info.put("path", getMapping.path());
            requestMappingHandlerMapping
                .registerMapping(RequestMappingInfo.paths(getMapping.value()).methods(RequestMethod.GET).build(),
                    beanName, method);
            return info;
          }
          PostMapping postMapping = method.getAnnotation(PostMapping.class);
          if (postMapping != null) {
            info.put("pattern", postMapping.value());
            info.put("method", HttpMethod.POST.name());
            info.put("path", postMapping.path());
            requestMappingHandlerMapping
                .registerMapping(RequestMappingInfo.paths(postMapping.value()).methods(RequestMethod.POST).build(),
                    beanName, method);
            return info;
          }
          DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
          if (deleteMapping != null) {
            info.put("pattern", deleteMapping.value());
            info.put("method", HttpMethod.DELETE.name());
            info.put("path", deleteMapping.path());
            requestMappingHandlerMapping
                .registerMapping(RequestMappingInfo.paths(deleteMapping.value()).methods(RequestMethod.DELETE).build(),
                    beanName, method);
            return info;
          }
          PutMapping putMapping = method.getAnnotation(PutMapping.class);
          if (putMapping != null) {
            info.put("pattern", putMapping.value());
            info.put("method", HttpMethod.PUT.name());
            info.put("path", putMapping.path());
            requestMappingHandlerMapping
                .registerMapping(RequestMappingInfo.paths(putMapping.value()).methods(RequestMethod.PUT).build(),
                    beanName, method);
            return info;
          }
          PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
          if (putMapping != null) {
            info.put("pattern", patchMapping.value());
            info.put("method", HttpMethod.PATCH.name());
            info.put("path", patchMapping.path());
            requestMappingHandlerMapping
                .registerMapping(RequestMappingInfo.paths(patchMapping.value()).methods(RequestMethod.PUT).build(),
                    beanName, method);
            return info;
          }
          return info;
        }).collect(
            Collectors.toList()));
    return item;
  }

  public void unRegisterMapping(String beanName) {
    if (!beanFactory.containsBean(beanName)) {
      return;
    }
    Object bean = beanFactory.getBean(beanName);
    Arrays.stream(bean.getClass().getMethods())
        .filter(
            method -> Arrays.stream(method.getAnnotations())
                .anyMatch(
                    annotation -> annotation.annotationType().isAnnotationPresent(RequestMapping.class) || annotation
                        .annotationType().equals(RequestMapping.class)))
        .forEach(method -> {
          RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
          if (requestMapping != null) {
            requestMappingHandlerMapping.unregisterMapping(RequestMappingInfo.paths(requestMapping.value())
                .methods(requestMapping.method().length > 0 ? requestMapping.method() : RequestMethod.values())
                .build());
          }
          GetMapping getMapping = method.getAnnotation(GetMapping.class);
          if (getMapping != null) {
            requestMappingHandlerMapping
                .unregisterMapping(RequestMappingInfo.paths(getMapping.value()).methods(RequestMethod.GET).build());
          }
          PostMapping postMapping = method.getAnnotation(PostMapping.class);
          if (postMapping != null) {
            requestMappingHandlerMapping
                .unregisterMapping(RequestMappingInfo.paths(postMapping.value()).methods(RequestMethod.POST).build());
          }
          DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
          if (deleteMapping != null) {
            requestMappingHandlerMapping.unregisterMapping(
                RequestMappingInfo.paths(deleteMapping.value()).methods(RequestMethod.DELETE).build());
          }
          PutMapping putMapping = method.getAnnotation(PutMapping.class);
          if (putMapping != null) {
            requestMappingHandlerMapping
                .unregisterMapping(RequestMappingInfo.paths(putMapping.value()).methods(RequestMethod.PUT).build());
          }
          PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
          if (patchMapping != null) {
            requestMappingHandlerMapping
                .unregisterMapping(RequestMappingInfo.paths(putMapping.value()).methods(RequestMethod.PATCH).build());
          }
        });
    deleteBean(beanName);
  }

  public Object getMappings() {
    return beanFactory.getBeansWithAnnotation(Controller.class).values().stream().map(bean -> {
      Map item = new HashMap();
      item.put("beanClassName", bean.getClass().getName());
      return item;
    }).collect(Collectors.toList());
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
    this.applicationContext = applicationContext;
    this.beanFactory = (DefaultListableBeanFactory) configurableApplicationContext
        .getBeanFactory();
    try {
      loadJars(new URL("file:\\D:\\juggling-simple-2.jar"));
    } catch (MalformedURLException e) {
      e.printStackTrace();
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

  private void loadJars(URL... urls) {
    if (urls.length == 0) {
      return;
    }
    // 使用URLClassLoader
    URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);
    this.beanFactory.setBeanClassLoader(urlClassLoader);
  }

  public Object getAService() {
    return Arrays.stream(beanFactory.getBeanNamesForType(AInterface.class)).map(beanName -> {
      System.out.println(beanName + " containsBean: " + beanFactory.containsBean(beanName));
      return beanFactory.getBean(beanName).getClass().getName();
    }).collect(Collectors.toList());
  }

  public List getContextBean() {
    return Arrays.stream(beanFactory.getBeanNamesForType(Object.class)).filter(beanName -> {
      Object bean = beanFactory.getBean(beanName);
      return bean instanceof BeanFactory;
    }).collect(Collectors.toList());
  }
}
