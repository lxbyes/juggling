package me.leckie.juggling.redis.service.impl;

import java.io.Serializable;
import me.leckie.juggling.redis.service.UserCacheService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version $Id: UserCacheServiceImpl.java, v0.1 2018/10/23 14:06 Leckie Exp $$
 */
@Service
public class UserCacheServiceImpl implements UserCacheService {

  private RedisTemplate<String, Serializable> redisTemplate;

  public UserCacheServiceImpl(RedisTemplate<String, Serializable> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Override
  public RedisTemplate<String, Serializable> redisTemplate() {
    return this.redisTemplate;
  }
}
