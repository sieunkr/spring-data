package com.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {

    @Query("select * from Coffee c where c.coffeename = :coffeename")
    Coffee findByName(@Param("coffeename") String coffeename);

}
