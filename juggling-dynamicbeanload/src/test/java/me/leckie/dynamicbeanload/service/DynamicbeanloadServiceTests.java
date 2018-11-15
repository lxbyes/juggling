package me.leckie.dynamicbeanload.service;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version $Id: DynamicbeanloadServiceTests.java, v0.1 2018/11/14 11:57 Leckie Exp $$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DynamicbeanloadServiceTests {

  @Autowired
  private DynamicbeanloadService dynamicbeanloadService;

  @Test
  public void getContextBean() {
    List contextBeans = dynamicbeanloadService.getContextBean();
    contextBeans.forEach(System.out::println);
  }
}