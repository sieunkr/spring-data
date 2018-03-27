package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> list(){

        cityService.addCity(new City("na", "haha"));

        return cityService.listCity();
    }

}
