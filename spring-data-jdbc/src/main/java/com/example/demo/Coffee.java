package com.example.demo;

import org.springframework.data.annotation.Id;


public class Coffee {

    @Id
    public long id;

    public String coffeename;

    public Integer price;
}
