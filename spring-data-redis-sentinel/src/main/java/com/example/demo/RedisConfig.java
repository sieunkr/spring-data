package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
//@PropertySource("/properties/redis.properties")
public class RedisConfig {


    @Value("${redis.master}")
    private String redisMaster;

    @Value("${redis.sentinels.host}")
    private String redisHost;

    @Value("${redis.sentinels.port}")
    private Integer redisPort;


    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {

        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().master(redisMaster)
                .sentinel(redisHost, redisPort);

        return new JedisConnectionFactory(sentinelConfig);
    }

}

