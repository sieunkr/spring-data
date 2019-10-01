package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
public class CafeRepositoryTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void shouldSave_toRedis() {

        String key = "key:say";

        redisTemplate.opsForValue().set(key, "Hello");
        String value = (String)redisTemplate.opsForValue().get(key);

        Assert.assertEquals("Hello", value);
    }
}
