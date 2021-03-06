package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class HomeController {

    @Autowired
    private RedisTemplate personRedisTemplate;

    @GetMapping
    @RequestMapping("/{firstname}")
    public String findByFirstName(@PathVariable(name = "firstname") String firstname){

        return (String) personRedisTemplate.opsForValue().get(firstname);
    }
}
