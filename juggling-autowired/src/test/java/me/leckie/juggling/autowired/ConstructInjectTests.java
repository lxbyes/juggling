package me.leckie.juggling.autowired;

import me.leckie.juggling.autowired.service.ConstructInjectComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author laixianbo
 * @version $Id: ConstructInjectTests.java, v0.1 2018/11/12 11:09 laixianbo Exp $$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConstructInjectTests {

  @Autowired
  private ConstructInjectComponent constructInjectComponent;

  @Test
  public void test() {
    constructInjectComponent.hello(getClass().getSimpleName());
  }

}
