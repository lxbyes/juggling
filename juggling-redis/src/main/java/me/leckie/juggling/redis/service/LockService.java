package me.leckie.juggling.redis.service;

import java.io.Serializable;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

/**
 * @author Leckie
 * @version LockService.java, v0.1 2019/8/5 14:19
 */
@Service
public class LockService {

  private static final String LOCK_PREFIX = "_lock_";

  private static final long LOCK_TIMEOUT_MILLS = 1000 * 30L;

  private static final long DEFAULT_GET_LOCK_TIMEOUT_MILLS = 1000 * 30L;

  private static final String MACHINE_MARK = "HI";

  @Autowired
  private RedisTemplate<String, Serializable> redisTemplate;

  public boolean lock(String key) {
    return lock(key, DEFAULT_GET_LOCK_TIMEOUT_MILLS, TimeUnit.MILLISECONDS);
  }

  public boolean lock(String key, long timeout, TimeUnit timeUnit) {
    long timeoutTime = System.currentTimeMillis() + timeUnit.toMillis(timeout);
    while (System.currentTimeMillis() < timeoutTime) {
      Boolean aBoolean = redisTemplate.opsForValue()
          .setIfAbsent(LOCK_PREFIX + key, MACHINE_MARK + Thread.currentThread().getName(), LOCK_TIMEOUT_MILLS,
              TimeUnit.MILLISECONDS);
      if (aBoolean) {
        return true;
      }
      try {
        TimeUnit.MILLISECONDS.sleep(500L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new RuntimeException("获取锁超时");
  }

  public boolean unlock(String key) {
    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    boolean result = (boolean) redisTemplate
        .execute(RedisScript.of(script, Boolean.class), Collections.singletonList(LOCK_PREFIX + key),
            MACHINE_MARK + Thread.currentThread().getName());
    return result;
  }
}
