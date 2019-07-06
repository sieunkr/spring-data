package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonUseCase personUseCase;

    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @GetMapping("/person")
    public Person findByName(@RequestParam(name = "name") String name){
        return personUseCase.findByName(name);
    }
}