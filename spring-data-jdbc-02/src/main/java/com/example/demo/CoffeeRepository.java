package com.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {

    @Query("select * from coffee c where c.name = :name")
    Coffee findByName(@Param("name") String name);


}
