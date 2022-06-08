package com.xupt.configer;

import static java.util.Collections.singletonMap;

import java.time.Duration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer<Object>(Object.class);
    template.setConnectionFactory(factory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
    template.setValueSerializer(objectJackson2JsonRedisSerializer);
    template.afterPropertiesSet();
    return template;
  }

  /**
   * 缓存配置管理器
   *
   * @param factory
   * @return
   */
  @Bean()
  public CacheManager cacheManager(LettuceConnectionFactory factory) {
    // 配置序列化（缓存默认有效期 6小时）
    RedisCacheConfiguration config =
        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(6));
    RedisCacheConfiguration redisCacheConfiguration =
        config
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    new GenericJackson2JsonRedisSerializer()));

    /* 自定义配置test:demo 的超时时间为 5分钟*/
    RedisCacheManager cacheManager =
        RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(factory))
            .cacheDefaults(redisCacheConfiguration)
            .withInitialCacheConfigurations(
                singletonMap(
                    "test:demo",
                    RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(5))
                        .disableCachingNullValues()))
            .transactionAware()
            .build();
    return cacheManager;
  }
}
