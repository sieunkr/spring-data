package com.example.demo;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query("select * from city c where c.name = :name")
    City findByName(@Param("name") String name);

    @Query("select * from city where sleep(1000)")
    List<City> findAll();

}
