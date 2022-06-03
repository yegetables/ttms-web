package com.xupt.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
  @Resource RedisTemplate<String, Object> redisTemplate;

  public String get(String key) {
    return (String) redisTemplate.opsForValue().get(key);
  }

  public void set(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public boolean set(String key, Object value, long time) {
    try {
      if (time > 0) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
      } else {
        set(key, value);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public void setList(String key, Object value) {
    redisTemplate.opsForList().leftPush(key, value);
  }

  public List<Object> getAllList(String key) {
    List<Object> res = redisTemplate.opsForList().range(key, 0, -1);
    return res;
  }

  public boolean delete(String uid) {
    Boolean delete = redisTemplate.delete(uid);
    return delete;
  }

  public void expire(String key, int day) {
    redisTemplate.expire(key, 1, TimeUnit.DAYS);
  }

  public void lRemove(String key) {
    redisTemplate.opsForList().trim(key, 1, 0);
  }
}
