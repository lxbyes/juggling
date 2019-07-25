package me.leckie.juggling.springsession.web.controller;

import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpSession;
import me.leckie.juggling.springsession.model.Gender;
import me.leckie.juggling.springsession.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leckie
 * @version LoginController.java, v0.1 2019-07-24 10:58
 */
@RestController
public class LoginController {

  @Autowired
  private StringRedisTemplate redisTemplate;

  @PostMapping("/login")
  public User login(@RequestBody Map<String, String> loginInfo, HttpSession session) {
    Assert.isTrue(loginInfo.containsKey("username") && loginInfo.containsKey("password"), "用户名或密码错误");
    String username = loginInfo.get("username");
    String password = loginInfo.get("password");
    Boolean isMember = redisTemplate.boundSetOps("juggling_user_list").isMember(username);
    Assert.isTrue(isMember, "用户名或密码错误");
    Object realPassword = redisTemplate.boundHashOps("user_" + username).get("password");
    Assert.isTrue(password != null && password.equals(realPassword), "用户名或密码错误");
    User user = new User();
    user.setGender(Gender.FEMALE);
    user.setId(Integer.toUnsignedLong(username.hashCode()));
    user.setName("Mary Bryant");
    user.setNickName("Mary");
    user.setRoles(Arrays.asList("USER", "ADMIN"));
    session.setAttribute("CURRENT_USER", user);
    return user;
  }
}
