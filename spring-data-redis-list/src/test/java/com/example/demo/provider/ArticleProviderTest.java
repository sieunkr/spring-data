package com.example.demo.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleProviderTest {

    @Autowired
    private ArticleProvider articleProvider;

    private final String REDIS_PREFIX_KEY = "articles:";
    private final int ARTICLE_MAX_SIZE = 10;

    @Test
    public void testAdd() {

        articleProvider.deleteById("sieunkim");

        for(int i = 1; i <= 30; i++){
            Instant instant = Instant.now();
            long timeStampMillis = instant.toEpochMilli();
            articleProvider.setScore("sieunkim", "@springboot/" + i, timeStampMillis);
        }
    }

    @Test
    public void recentlyArticlesById() {

        List<String> list = articleProvider.recentlyArticlesById("sieunkim");
        assertEquals(5, list.size());
        assertEquals("@springboot/30", list.get(0));
    }

    @Test
    public void setScore() {
    }
}