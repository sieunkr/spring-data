package com.example.demo.core;

import lombok.Data;

//TODO:실무에서는 lombok.Data 사용하지 말자
@Data
public class Article {
    private String articleUrl;
    private String author;
    private String description;
}
