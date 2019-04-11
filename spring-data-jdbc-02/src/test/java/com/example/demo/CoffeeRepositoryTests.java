package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoffeeRepositoryTests {

    @Autowired
    CoffeeRepository coffeeRepository;

    @Test
    public void coffee_test(){

        Coffee coffee = new Coffee();
        coffee.name = "mocha";
        coffee.price = 1200;

        coffeeRepository.save(coffee);

        Coffee mocha = coffeeRepository.findByName("mocha");
        assertTrue(mocha.name.equals("mocha"));
        assertFalse(mocha.name.equals("latte"));
    }

}
