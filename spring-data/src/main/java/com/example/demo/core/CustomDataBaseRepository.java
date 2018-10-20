package com.example.demo.core;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomDataBaseRepository<T, ID> extends CrudRepository<T, ID> {

    List<T> findAll();
    T findByName(String name);
}
