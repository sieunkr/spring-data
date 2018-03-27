package spring.data.arangodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.data.arangodb.entity.Coffee;
import spring.data.arangodb.entity.CoffeeRepository;

@RestController
public class HomeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping
    public String home(){

        final Coffee coffee = new Coffee("모카", 1200);

        //com.arangodb.ArangoDBException: Response: 404, Error: 1202 - document not found
        coffeeRepository.save(coffee);


        return "";
    }

}
