package me.leckie.dynamicbeanload.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author Leckie
 * @version $Id: DynamicWebService.java, v0.1 2018/11/19 17:24 Leckie Exp $$
 */
@Service
public class DynamicWebService implements ApplicationContextAware {

  private AnnotationConfigServletWebServerApplicationContext applicationContext;

  private AnnotationConfigApplicationContext subApplicationContext;

  private RequestMappingHandlerMapping requestMappingHandlerMapping;

  private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = (AnnotationConfigServletWebServerApplicationContext) applicationContext;
  }

  @PostConstruct
  public void init() throws MalformedURLException {
    subApplicationContext = new AnnotationConfigApplicationContext();
    subApplicationContext.setParent(applicationContext);
    // subApplicationContext.setServletContext(applicationContext.getServletContext());
    URLClassLoader urlClassLoader = URLClassLoader.newInstance(
        new URL[]{new URL("file:D:\\git\\juggling\\juggling-simple\\target\\juggling-simple-1.0-SNAPSHOT.jar")});
    subApplicationContext.setClassLoader(urlClassLoader);
    subApplicationContext.setAllowBeanDefinitionOverriding(true);
    subApplicationContext.scan("me.leckie.juggling.simple");
    subApplicationContext.refresh();
    subApplicationContext.start();

    subApplicationContext.registerBeanDefinition("requestMappingHandlerMapping",
        BeanDefinitionBuilder.genericBeanDefinition(RequestMappingHandlerMapping.class).getBeanDefinition());
    subApplicationContext.registerBeanDefinition("requestMappingHandlerAdapter",
        BeanDefinitionBuilder.genericBeanDefinition(RequestMappingHandlerAdapter.class)
            .getBeanDefinition());
    requestMappingHandlerMapping = (RequestMappingHandlerMapping) subApplicationContext
        .getBean("requestMappingHandlerMapping");
    requestMappingHandlerAdapter = (RequestMappingHandlerAdapter) subApplicationContext
        .getBean("requestMappingHandlerAdapter");
   /* subApplicationContext.registerBeanDefinition(StringHttpMessageConverter.class.getName(),
        BeanDefinitionBuilder.genericBeanDefinition(StringHttpMessageConverter.class.getName()).getBeanDefinition());*/
    subApplicationContext.registerBeanDefinition(MappingJackson2HttpMessageConverter.class.getName(),
        BeanDefinitionBuilder.genericBeanDefinition(MappingJackson2HttpMessageConverter.class)
            .getBeanDefinition());
    List<HttpMessageConverter<?>> messageConverters = requestMappingHandlerAdapter.getMessageConverters();
    messageConverters.addAll(subApplicationContext.getBeansOfType(MappingJackson2HttpMessageConverter.class).values());
    // add MappingJackson2HttpMessageConverter support
    requestMappingHandlerAdapter.setMessageConverters(messageConverters);
    Arrays.stream(subApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
  }

  public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HandlerExecutionChain mappingHandler = requestMappingHandlerMapping.getHandler(request);
    Object handler = mappingHandler.getHandler();
    System.out.println(handler.getClass().getName());
    System.out.println(handler);
    requestMappingHandlerAdapter.getMessageConverters().forEach(System.out::println);
    return requestMappingHandlerAdapter.handle(request, response, handler);
  }

  public Object getMappings() {
    Map<String, String> map = new HashMap<>();
    requestMappingHandlerMapping.getHandlerMethods().forEach((k, v) -> {
      map.put(k.toString(), v.toString());
    });
    return map;
  }

  public Object registerMapping(String beanName) {
    Object bean = subApplicationContext.getBean(beanName);
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
                .registerMapping(RequestMappingInfo.paths(putMapping.value()).methods(RequestMethod.PUT)
                        .produces("application/json;charset=UTF-8").consumes("application/json;charset=UTF-8").build(),
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
}
