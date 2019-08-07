package me.leckie.juggling.redis.service;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Leckie
 * @version LockServiceTest.java, v0.1 2019/8/5 15:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LockServiceTest {

  @Autowired
  private LockService lockService;

  @Test
  public void lock() {
    boolean a = lockService.lock("a");
    System.out.println(a);
  }

  @Test
  public void lockWithTimeOut() {
    boolean result = lockService.lock("a", 18L, TimeUnit.SECONDS);
    System.out.println(result);
  }

  @Test
  public void unlock() {
    boolean a = lockService.unlock("a");
    System.out.println(a);
  }
}