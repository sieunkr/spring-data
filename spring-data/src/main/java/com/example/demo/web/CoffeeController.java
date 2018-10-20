package com.example.demo.web;

import com.example.demo.core.CustomDataBaseRepository;
import com.example.demo.core.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    @Autowired
    private final CustomDataBaseRepository customDataBaseRepository;

    public CoffeeController(CustomDataBaseRepository customDataBaseRepository) {
        this.customDataBaseRepository = customDataBaseRepository;
    }


    @PostMapping
    public String init(){

        customDataBaseRepository.save(new Coffee(1,"모카",1200));
        customDataBaseRepository.save(new Coffee(2,"라떼",1100));
        customDataBaseRepository.save(new Coffee(3,"아메리카노",900));

        return "OK";
    }

    @GetMapping
    public List<Coffee> list(){
        return customDataBaseRepository.findAll();
    }

    @GetMapping("/{name}")
    public Coffee find(@PathVariable(name = "name") String name){

        return (Coffee) customDataBaseRepository.findByName(name);
    }

}
