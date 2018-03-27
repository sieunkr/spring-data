package com.example.demo.service;

import com.example.demo.entity.City;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> listCity(){
        return cityRepository.findAll();
    }

    public void addCity(City city){
        cityRepository.save(city);
    }
}
