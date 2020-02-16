package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final RedisTemplate colorRedisTemplate;
    private final static String REDIS_KEY_PRIFIX = "color:";
    private final static int MAX_COLOR_COUNT = 100000;

    public List<ColorDTO> mget(List<String> keyList){
        return colorRedisTemplate.opsForValue().multiGet(keyList);
    }

    public void init() {
        IntStream.range(1, MAX_COLOR_COUNT).forEach(id -> makeColor(String.valueOf(id)));
    }

    private void makeColor(String id) {

        Assert.notNull(id, "Color's ID must not be null!!");

        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        colorRedisTemplate.opsForValue().set(REDIS_KEY_PRIFIX + id, new ColorDTO(id, r, g, b));
    }
}
