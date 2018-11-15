package me.leckie.juggling.redis.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import me.leckie.juggling.redis.UnitTestBase;
import me.leckie.juggling.redis.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leckie
 * @version $Id: UserCacheServiceTests.java, v0.1 2018/10/23 14:12 Leckie Exp $$
 */
public class UserCacheServiceTests extends UnitTestBase {

  @Autowired
  private UserCacheService userCacheService;

  @Test
  public void findByKey() {
    String name = "Leckie";
    User leckie = userCacheService.findByKey(name);
    Assert.assertNotNull(leckie);
    Assert.assertEquals(name, leckie.getName());
  }


  @Test
  public void findByKeyNotFound() {
    String name = "LeckieLai";
    User leckie = userCacheService.findByKey(name);
    Assert.assertNull(leckie);
  }

  @Test
  public void findAll() {
    Collection<User> all = userCacheService.findAll(Arrays.asList("multi1", "multi2"));
    Assert.assertNotNull(all);
    Assert.assertTrue(all.size() > 0);
  }

  @Test
  public void set() {
    User leckie = new User();
    leckie.setName("Leckie");
    leckie.setBirthday(LocalDate.of(1990, 10, 22));
    leckie.setGender("男");
    userCacheService.set(leckie.getName(), leckie);
    Assert.assertNotNull(leckie.getId());
  }

  @Test
  public void setWithTimeout() {
    User zhangsan = new User();
    zhangsan.setName("zhangsan");
    zhangsan.setBirthday(LocalDate.of(1991, 10, 22));
    zhangsan.setGender("男");
    userCacheService.set(zhangsan.getName(), zhangsan, 20, TimeUnit.SECONDS);
    Assert.assertNotNull(zhangsan.getId());
  }

  @Test
  public void multiSet() {
    Map<String, User> map = new LinkedHashMap<>();
    User multi1 = new User();
    multi1.setName("multi1");
    multi1.setBirthday(LocalDate.of(1990, 10, 22));
    multi1.setGender("男");
    User multi2 = new User();
    multi2.setName("multi2");
    multi2.setBirthday(LocalDate.of(1990, 10, 25));
    multi2.setGender("女");
    map.put(multi1.getName(), multi1);
    map.put(multi2.getName(), multi2);
    userCacheService.multiSet(map);
    Assert.assertNotNull(map);
  }
}