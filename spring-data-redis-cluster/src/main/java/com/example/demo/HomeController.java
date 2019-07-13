package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public String test(){

        //redisTemplate.opsForValue().set("kim","eddy");

        String result = (String) redisTemplate.opsForValue().get("kim");

        return result;
    }
}
