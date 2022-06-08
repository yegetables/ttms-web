package com.xupt.configer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    // Use Jackson 2Json RedisSerializer to serialize and deserialize the value of redis (default
    // JDK serialization)
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
        new Jackson2JsonRedisSerializer(Object.class);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    // 将类名称序列化到json串中
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    // 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

    // Use String RedisSerializer to serialize and deserialize the key value of redis
    RedisSerializer redisSerializer = new StringRedisSerializer();
    // key
    redisTemplate.setKeySerializer(redisSerializer);
    redisTemplate.setHashKeySerializer(redisSerializer);
    // value
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }
}
