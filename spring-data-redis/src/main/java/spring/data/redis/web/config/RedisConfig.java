package spring.data.redis.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan("spring.data.redis.web")
public class RedisConfig {

    private @Value("${spring.redis.host}") String redisHost;
    private @Value("${spring.redis.port}") int redisPort;
    private @Value("${spring.redis.password}") String password;


    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(30);
        jedisPoolConfig.setMinIdle(10);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }


    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        jedisConnectionFactory.setHostName(redisHost);
        jedisConnectionFactory.setPort(redisPort);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setUsePool(true);
        return  jedisConnectionFactory;
    }


    @Bean
    public RedisTemplate<String , Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        template.setEnableDefaultSerializer(false);
        template.setEnableTransactionSupport(true);
        return template;
    }



}
