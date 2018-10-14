package com.example.demo;

import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;


public class Cafe {

    @Id
    public long id;

    public String title;

    private Map<String, Coffee> coffeeMap = new HashMap<>();

    public void addCoffee(String name, Integer price) {

        Coffee coffee = new Coffee();
        coffee.coffeename = name;
        coffee.price = price;
        coffeeMap.put(name, coffee);
    }

}
