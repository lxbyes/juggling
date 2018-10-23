/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.redis.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author laixianbo
 * @version $Id: CacheService.java, v0.1 2018/10/23 9:49 laixianbo Exp $$
 */
public interface CacheService<K, T extends Serializable> {

  RedisTemplate<K, Serializable> redisTemplate();

  default T findByKey(K key) {
    return (T) redisTemplate().opsForValue().get(key);
  }

  default Collection<T> findAll(Collection<K> keys) {
    return (Collection<T>) redisTemplate().opsForValue().multiGet(keys);
  }

  default void set(K key, T value) {
    redisTemplate().opsForValue().set(key, value);
  }

  default void set(K key, T value, long timeout, TimeUnit timeUnit) {
    redisTemplate().opsForValue().set(key, value, timeout, timeUnit);
  }

  default void multiSet(Map<? extends K, ? extends T> map) {
    redisTemplate().opsForValue().multiSet(map);
  }
}
