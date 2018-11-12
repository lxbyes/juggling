package me.leckie.juggling.treasure.web.controller;

import javax.management.MBeanServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author laixianbo
 * @version $Id: SampleJmxTests.java, v0.1 2018/8/17 15:31 laixianbo Exp $$
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.jmx.enabled=true")
@DirtiesContext
public class SampleJmxTests {

  @Autowired
  private MBeanServer mBeanServer;

  @Test
  public void exampleTest() {
  }

}
