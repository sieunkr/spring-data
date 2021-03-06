package com.example.demo.provider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleProviderTest {

    @Autowired
    private ArticleProvider articleProvider;

    private final String REDIS_PREFIX_KEY = "articles:";
    private final int ARTICLE_MAX_SIZE = 10;

    @Before
    public void 테스트를_위한_샘플_데이터_저장() {

        articleProvider.deleteById("sieunkim");

        for(int i = 1; i <= 5; i++){
            Instant instant = Instant.now();
            long timeStampMillis = instant.toEpochMilli();
            articleProvider.setScore("sieunkim", "http://url/@springboot/" + i, timeStampMillis);
        }
    }

    @Test
    public void 최근에_봤던_기사_5개를_조회할수_있는가() {

        List<String> list = articleProvider.recentlyArticlesById("sieunkim");
        assertEquals(5, list.size());
        assertEquals("http://url/@springboot/5", list.get(0));
    }


    @Test
    public void 기존에_방문했던_중복URL을_저장() {
        articleProvider.setScore("sieunkim", "http://url/@springboot/" + 3, 0L);

        List<String> list = articleProvider.recentlyArticlesById("sieunkim");
        assertEquals(5, list.size());
        assertEquals("http://url/@springboot/3", list.get(0));
        assertEquals("http://url/@springboot/3", list.get(3));

    }

}