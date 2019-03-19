package com.example.demo;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeRepository coffeeRepository;

    public CoffeeController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @GetMapping
    public Flux<Coffee> findAll(){
        return coffeeRepository.findAll();
    }

    @GetMapping("{name}")
    public Flux<Coffee> findByName(@PathVariable(name = "name") String name){
        return coffeeRepository.findByName(name);
    }
}
