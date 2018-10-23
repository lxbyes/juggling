/**
 * BBD Service Inc All Rights Reserved @2018
 */
package me.leckie.juggling.redis.configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.Serializable;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author laixianbo
 * @version $Id: RedisConfiguration.java, v0.1 2018/10/23 9:20 laixianbo Exp $$
 */
@Configuration
@Import(RedisAutoConfiguration.class)
public class RedisConfiguration {

  @Bean
  public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
    RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setConnectionFactory(connectionFactory);
    return redisTemplate;
  }

  @Bean
  public Module jdk8Module() {
    return new Jdk8Module();
  }

  @Bean
  public Module javaTimeModule() {
    return new JavaTimeModule();
  }

}
