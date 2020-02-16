package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static io.lettuce.core.ReadFrom.SLAVE_PREFERRED;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(SLAVE_PREFERRED)
                .commandTimeout(Duration.ofSeconds(1))
                .build();

        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration()
                .clusterNode("redis-cluster",6379);
                /*
                .clusterNode("10.1.4.77",6379)
                .clusterNode("10.1.4.78",6379)
                .clusterNode("10.1.4.79",6379)
                .clusterNode("10.1.4.80",6379)
                .clusterNode("10.1.4.81",6379);
                 */
        return new LettuceConnectionFactory(clusterConfiguration, clientConfig);
    }

    @Bean
    public RedisTemplate<String, ColorDTO> colorRedisTemplate(){
        RedisTemplate<String, ColorDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }
}
