package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		jdbcTemplate.execute("DROP TABLE cafe IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE coffees(" +
				"id SERIAL, name VARCHAR(255))");

		List<String> coffees = Arrays.asList("mocha", "latte", "americano");

		//jdbcTemplate.batchUpdate("INSERT INTO... 생략
		//jdbcTemplate.query("SELECT id...생략

	}
}
