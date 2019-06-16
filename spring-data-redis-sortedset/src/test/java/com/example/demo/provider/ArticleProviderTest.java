package com.example.demo.provider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest  //TODO:테스트에 필요한 컴포넌트만 가져오도록 변경
public class ArticleProviderTest {

    @Autowired
    private ArticleProvider articleProvider;

    @Test
    public void testAdd() {
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