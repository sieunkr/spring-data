package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final CoffeeRepository coffeeRepository;

    public DemoApplication(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        coffeeRepository.save(new Coffee(1, "모카", 1200)).subscribe();
        coffeeRepository.save(new Coffee(2, "라떼", 1100)).subscribe();
        coffeeRepository.save(new Coffee(3, "아메리카노", 900)).subscribe();
        */
    }
}
