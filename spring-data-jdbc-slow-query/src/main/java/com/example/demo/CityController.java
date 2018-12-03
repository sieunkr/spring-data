package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityUseCase cityUseCase;

    public CityController(CityUseCase cityUseCase) {
        this.cityUseCase = cityUseCase;
    }


    @GetMapping("/{name}")
    public City findByName(@PathVariable(name = "name") String name){
        return cityUseCase.findByName(name);
    }

    @GetMapping
    public List<City> findAll(){
        return cityUseCase.findAll();
    }



}
