package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Value("${redis.master}")
    private String redisMaster;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.sentinel.nodes}")
    private String redisNodes;

    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {

        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().master(redisMaster);

        String[] host = redisNodes.split(",");
        for(String redisHost : host){
            String[] item = redisHost.split(":");
            String ip = item[0];
            String port = item[1];
            sentinelConfig.addSentinel(new RedisNode(ip, Integer.parseInt(port)));
        }


        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxIdle(50);
        poolConfig.setMinIdle(10);
        poolConfig.setMaxWaitMillis(12000);
        poolConfig.setBlockWhenExhausted(true); //확인 필요
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setTimeBetweenEvictionRunsMillis(3600000);
        poolConfig.setNumTestsPerEvictionRun(5);
        poolConfig.setMinEvictableIdleTimeMillis(300000);
        poolConfig.setSoftMinEvictableIdleTimeMillis(300000);


        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setPoolConfig(poolConfig);

        return jedisConnectionFactory;
    }

}
