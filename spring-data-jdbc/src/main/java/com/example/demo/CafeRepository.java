package com.example.demo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CafeRepository extends CrudRepository<Cafe, Long> {

    @Query("select * from Cafe c where c.title = :title")
    Cafe findByName(@Param("title") String title);
}
