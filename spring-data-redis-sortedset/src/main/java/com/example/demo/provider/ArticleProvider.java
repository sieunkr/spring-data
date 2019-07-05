package com.example.demo.provider;

import com.example.demo.core.ArticleUseCase;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ArticleProvider implements ArticleUseCase {

    private final RedisTemplate<String,String> redisTemplate;
    private final ZSetOperations<String, String> zSetOperations;
    private final String REDIS_PREFIX_KEY = "articles:";
    private final int ARTICLE_MAX_SIZE = 5;

    public ArticleProvider(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    @Override
    public List<String> recentlyArticlesById(String nid) {
        //reverseRange는 LinkedHashMap 으로 리턴 됨
        return new ArrayList<>(Objects.requireNonNull(zSetOperations.reverseRange(REDIS_PREFIX_KEY + nid, 0, -1)));
    }

    @Override
    public void setScore(String nid, String articleUrl, long timestamp) {
        zSetOperations.add(REDIS_PREFIX_KEY + nid, articleUrl, timestamp);
        zSetOperations.removeRange(REDIS_PREFIX_KEY + nid, -(ARTICLE_MAX_SIZE + 1), -(ARTICLE_MAX_SIZE + 1));
    }

    @Override
    public void deleteById(String nid) {
        redisTemplate.delete(REDIS_PREFIX_KEY + nid);
    }

}
