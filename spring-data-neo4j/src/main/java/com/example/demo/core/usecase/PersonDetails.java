package com.example.demo.core.usecase;

import com.example.demo.core.entity.Person;

public interface PersonDetails {
    Person findByName(String name);
}
