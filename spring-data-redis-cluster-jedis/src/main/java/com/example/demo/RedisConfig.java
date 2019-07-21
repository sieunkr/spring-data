package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration()
                .clusterNode("192.168.19.136",6379)
                .clusterNode("192.168.19.137",6379)
                .clusterNode("192.168.19.138",6379)
                .clusterNode("192.168.19.136",6380)
                .clusterNode("192.168.19.137",6380)
                .clusterNode("192.168.19.138",6380);

        return new JedisConnectionFactory(clusterConfiguration);
    }

    @Bean
    public RedisTemplate<String, String> personRedisTemplate(){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
