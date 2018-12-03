package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityUseCase {

    private final CityRepository cityRepository;

    public CityUseCase(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City findByName(String name){
        return cityRepository.findByName(name);
    }

    public List<City> findAll(){
        return cityRepository.findAll();
    }

}
