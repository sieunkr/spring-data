package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoffeeRepository extends MongoRepository<Coffee, Integer> {
}
