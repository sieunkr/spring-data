package com.example.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "coffee")
@Data
public class Coffee {

    @Id
    private String id;
    private String name;
    private Integer price;
    private Object data;
}
