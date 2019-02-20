package com.example.demo;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final ReactiveRedisOperations<String, Coffee> coffeeOps;

    public CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
        this.coffeeOps = coffeeOps;
    }

    @GetMapping
    public Flux<Coffee> findCoffees(){

        return coffeeOps.keys("*")
                .flatMap(coffeeOps.opsForValue()::get);
    }

}
