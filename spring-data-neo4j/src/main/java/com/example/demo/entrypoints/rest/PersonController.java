package com.example.demo.entrypoints.rest;

import com.example.demo.core.entity.Person;
import com.example.demo.core.usecase.PersonUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonUseCase personUseCase;

    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @GetMapping
    public Person findByName(@RequestParam String name){
        return personUseCase.findByName(name);
    }
}
