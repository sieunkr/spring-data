package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static io.lettuce.core.ReadFrom.SLAVE_PREFERRED;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(SLAVE_PREFERRED)
                .build();

        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration()
                .clusterNode("192.168.19.129",6379)
                .clusterNode("192.168.19.130",6379)
                .clusterNode("192.168.19.131",6379)
                .clusterNode("192.168.19.129",6380)
                .clusterNode("192.168.19.130",6380)
                .clusterNode("192.168.19.131",6380);

        return new LettuceConnectionFactory(clusterConfiguration, clientConfig);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
