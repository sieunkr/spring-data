package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/contents")
public class HomController {

    private final ContentsReactiveRepository contentsReactiveRepository;

    public HomController(ContentsReactiveRepository contentsReactiveRepository) {
        this.contentsReactiveRepository = contentsReactiveRepository;
    }

    @GetMapping
    public Flux<Content> findAll(){
        return contentsReactiveRepository.findAll();
    }

    @GetMapping("/{name}")
    public Mono<Content> findAll(@PathVariable(name = "name") String name){
        return contentsReactiveRepository.findByName(name);
    }


}
