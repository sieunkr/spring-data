package spring.data.redis.cacheable.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.data.redis.cacheable.core.usecase.PersonUseCase;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final PersonUseCase personUseCase;

    @GetMapping("/{nid}")
    public Map findByName(@PathVariable String nid){

        return personUseCase.findByNid(nid);
    }


}
