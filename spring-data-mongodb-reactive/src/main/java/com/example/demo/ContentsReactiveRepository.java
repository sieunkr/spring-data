package com.example.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ContentsReactiveRepository extends ReactiveMongoRepository<Content, Integer> {

    Mono<Content> findByName(String name);

}
