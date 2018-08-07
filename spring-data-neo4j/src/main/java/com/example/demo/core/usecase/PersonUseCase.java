package com.example.demo.core.usecase;

import com.example.demo.core.entity.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonUseCase {

    private final PersonDetails personDetails;

    public PersonUseCase(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }

    public Person findByName(String name){
        return personDetails.findByName(name);
    }
}
