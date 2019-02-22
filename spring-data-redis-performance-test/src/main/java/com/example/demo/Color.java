package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
public class Color implements Serializable {

    private String name;
    private double red;
    private double blue;
    private double green;

    public Color(String name, double red, double blue, double green){
        this.name = name;
        this.red = red;
        this.blue = blue;
        this.green = green;
    }

}
