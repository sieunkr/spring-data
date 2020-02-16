package com.example.demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorDTO {
    private String id;
    private float r;
    private float g;
    private float b;

    public ColorDTO() {
    }

    public ColorDTO(String id, float r, float g, float b) {
        this.id = id;
        this.r = r;
        this.g = g;
        this.b = b;
    }
}