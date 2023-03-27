package com.example.townmarket.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
      RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
          .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
          .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
          .entryTtl(Duration.ofHours(1L));

      return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();

  }
}

//    RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
//        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

// 리소스 유형에 따라 만료 시간을 다르게 지정
//    Map<String, RedisCacheConfiguration> redisCacheConfigMap =new HashMap<>();
//    redisCacheConfigMap.put("loadUserDetails", defaultConfig.entryTtl(Duration.ofHours(1)));
//    redisCacheConfigMap.put(CacheNames.FEED, defaultConfig.entryTtl(Duration.ofSeconds(5L)));

//    RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
//        .withInitialCacheConfigurations(redisCacheConfigMap)
//        .build();

//    return redisCacheManager;
