package com.example.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class PersonProvider implements PersonUseCase {

    @Override
    @Cacheable(value = "person", key = "#name")
    public Person findByName(String name) {
        //TODO:데이터베이스 조회
        return Person.builder()
                    .name(name)
                    .age(1981)
                    .nickname("nickname-test")
                    .job("designer")
                    .build();
    }
}
