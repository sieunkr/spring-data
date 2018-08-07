package com.example.demo.core.repository;

import com.example.demo.core.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(@Param("name") String name);

}
