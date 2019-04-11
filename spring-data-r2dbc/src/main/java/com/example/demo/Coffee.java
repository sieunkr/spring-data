package com.example.demo;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffees")
@Data
public class Coffee {

    private Integer id;
    private String name;
    private Integer price;

    public Coffee(){
    }

    public Coffee(Integer id, String name, Integer price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

}