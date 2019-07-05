package com.example.demo.provider;

import com.example.demo.core.ArticleUseCase;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleProvider implements ArticleUseCase {

    private final RedisTemplate<String,String> redisTemplate;
    private final ListOperations<String, String> listOperations;
    private final String REDIS_PREFIX_KEY = "articles:";
    private final int ARTICLE_MAX_SIZE = 5;

    public ArticleProvider(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.listOperations = redisTemplate.opsForList();
    }

    @Override
    public List<String> recentlyArticlesById(String nid) {
        return listOperations.range(REDIS_PREFIX_KEY + nid, 0, 4);
    }

    @Override
    public void setScore(String nid, String articleUrl, long timestamp) {
        //TODO:timestamp 사용하지 않음
        listOperations.leftPush(REDIS_PREFIX_KEY + nid, articleUrl);
        listOperations.trim(REDIS_PREFIX_KEY + nid, 0, ARTICLE_MAX_SIZE - 1);

    }

    @Override
    public void deleteById(String nid) {
        redisTemplate.delete(REDIS_PREFIX_KEY + nid);
    }
}
