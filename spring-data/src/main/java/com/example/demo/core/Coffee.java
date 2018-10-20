package com.example.demo.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
public class Coffee implements Serializable {

    @Id
    private long id;
    private String name;
    private Integer price;

    public Coffee(long id, String name, Integer price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
