package me.leckie.juggling.context.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.jar.JarFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.loader.LaunchedURLClassLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.support.GenericWebApplicationContext;
import sun.net.www.protocol.jar.Handler;
import sun.net.www.protocol.jar.JarURLConnection;

/**
 * @author Leckie
 * @version $Id: JarTests.java, v0.1 2018/11/30 17:57 Leckie Exp $$
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class JarTests implements ApplicationContextAware {

  private GenericWebApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = (GenericWebApplicationContext) applicationContext;
  }

  @Test
  public void testReadNestedJar() throws InvocationTargetException, IllegalAccessException, MalformedURLException {
    AnnotationConfigApplicationContext subApplicationContext = new AnnotationConfigApplicationContext();
    subApplicationContext.setParent(applicationContext);
    LaunchedURLClassLoader urlClassLoader = new LaunchedURLClassLoader(
        new URL[]{new URL("file:\\D:\\test-demo.jar"), new URL("file:\\D:\\facade-11-SNAPSHOT-demo.jar")},
        getClass().getClassLoader());
    subApplicationContext.setClassLoader(urlClassLoader);
    subApplicationContext.setAllowBeanDefinitionOverriding(true);
    subApplicationContext.scan("com.bbd.test");
    subApplicationContext.refresh();
    // subApplicationContext.setBeanNameGenerator(new AnnotationBeanNameGenerator());
    subApplicationContext.start();
    Arrays.stream(subApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    String beanName = "test";
    /*Object bean = subApplicationContext.getBean(beanName);
    Method say = ClassUtils.getMethod(bean.getClass(), "sendMsg");
    say.invoke(bean);*/
    URL resource = urlClassLoader.getResource("lib/jedis-3.0.0-m1.jar");
    System.out.println(resource.getFile().getBytes());
  }

  @Test
  public void testReadManifest() throws IOException {
    URL url = new URL("file:\\D:\\test-demo.jar");
    JarURLConnection jarURLConnection = new JarURLConnection(url,
        new Handler());
    JarFile jarFile = jarURLConnection.getJarFile();
    jarFile.getManifest().getEntries().forEach((k, v) -> {
      System.out.println(k + ": " + v);
    });
  }
}
