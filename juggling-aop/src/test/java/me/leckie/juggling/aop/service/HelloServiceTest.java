package me.leckie.juggling.aop.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version HelloServiceTest.java, v0.1 2019-08-06 22:53
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class HelloServiceTest {

  @Autowired
  private HelloService helloService;

  @Test
  public void hello() {
    helloService.hello("Leckie");
    Assert.assertTrue(true);
  }
}