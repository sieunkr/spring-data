package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller("/custom")
public class CustomController {

    private final CustomRepository customRepository;

    public CustomController(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    @GetMapping
    public Flux<Coffee> findAll(){
        return customRepository.findByAll();
    }

}
