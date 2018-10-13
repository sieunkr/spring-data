package com.example.demo.repository;

import com.example.demo.Coffee;
import com.example.demo.CoffeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJdbcTest
public class CoffeeRepositoryTests {

    @Autowired
    CoffeeRepository coffeeRepository;

    @Test
    public void test(){

        Coffee coffee = new Coffee();
        coffee.coffeename = "mocha";
        coffee.price = 1200;

        coffeeRepository.save(coffee);

        Coffee mocha = coffeeRepository.findByName("mocha");
        assertTrue(mocha.coffeename.equals("mocha"));
        assertFalse(mocha.coffeename.equals("latte"));


    }
}
