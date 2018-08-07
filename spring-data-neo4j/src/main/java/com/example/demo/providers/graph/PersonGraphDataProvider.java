package com.example.demo.providers.graph;

import com.example.demo.core.entity.Person;
import com.example.demo.core.repository.PersonRepository;
import com.example.demo.core.usecase.PersonDetails;
import org.springframework.stereotype.Component;

@Component
public class PersonGraphDataProvider implements PersonDetails {

    private final PersonRepository personRepository;

    public PersonGraphDataProvider(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findByName(String name) {
        return personRepository.findByName(name);
    }
}
