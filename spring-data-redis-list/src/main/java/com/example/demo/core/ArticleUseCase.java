package com.example.demo.core;

import java.util.List;

public interface ArticleUseCase {

    List<String> recentlyArticlesById(String nid);
    void setScore(String nid, String articleUrl, long timestamp);
    void deleteById(String nid);
}
