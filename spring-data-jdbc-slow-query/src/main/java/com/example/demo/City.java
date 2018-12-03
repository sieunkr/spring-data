package com.example.demo;

import org.springframework.data.annotation.Id;

public class City {

    @Id
    public long id;
    public String name;
    public String state;
}
