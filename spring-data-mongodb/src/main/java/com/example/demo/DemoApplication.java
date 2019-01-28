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
	public void run(String... args) {

		coffeeRepository.findAll();
		System.out.println("테스트");

	}
}

