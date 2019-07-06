package com.example.demo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String nickname;
    private Integer age;
    private String job;
}
