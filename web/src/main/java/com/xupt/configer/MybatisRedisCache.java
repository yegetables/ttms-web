package com.xupt.configer;

import com.xupt.utils.RedisUtils;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.cache.Cache;
import org.springframework.stereotype.Component;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/3/6
 */
@Component
@Log4j2
public class MybatisRedisCache implements Cache {

  private RedisUtils RedisUtils;

  private RedisUtils getRedis() {
    return SpringContextUtils.getBean(RedisUtils.class);
  }

  private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  /** 缓存刷新时间（秒） */
  @Setter private long flushInterval = 0L;

  private String id;

  public MybatisRedisCache() {}

  public MybatisRedisCache(final String id) {
    if (id == null) {
      throw new IllegalArgumentException("Cache instances require an ID");
    }
    this.id = id;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void putObject(Object o, Object o1) {
    getRedis().hset(getId(), o.toString(), o1);
    if (flushInterval > 0L) {
      getRedis().expire(getId(), flushInterval);
    }
    log.info("[结果放入到缓存中: " + o.toString() + "=" + o1 + " ]");
  }

  @Override
  public Object getObject(Object o) {
    var value = getRedis().hget(getId(), o.toString());
    log.info("[从缓存中获取了: " + o.toString() + "=" + value + " ]");
    return value;
  }

  @Override
  public Object removeObject(Object o) {
    var value = getRedis().hdel(getId(), o);
    log.info("[从缓存删除了: " + o + "=" + value + " ]");
    return value;
  }

  @Override
  public void clear() {
    log.info("清空缓存!!!");
    getRedis().del(getId());
  }

  @Override
  public int getSize() {
    return getRedis().hsize(getId());
  }

  @Override
  public ReadWriteLock getReadWriteLock() {
    return readWriteLock;
  }
}
