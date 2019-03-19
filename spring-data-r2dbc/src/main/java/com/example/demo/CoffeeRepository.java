package com.example.demo;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CoffeeRepository extends ReactiveCrudRepository<Coffee, Integer> {

    @Query("SELECT * FROM COFFEES WHERE name = $1")
    Flux<Coffee> findByName(String name);
}