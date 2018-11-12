package me.leckie.juggling.autowired;

import me.leckie.juggling.autowired.service.InjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author laixianbo
 * @version $Id: AutowiredTests.java, v0.1 2018/11/12 11:09 laixianbo Exp $$
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AutowiredTests {

  @Autowired
  private InjectService injectService;

  @Test
  public void test() {
    injectService.hello(getClass().getSimpleName());
  }

}
