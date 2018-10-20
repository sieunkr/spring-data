package com.example.demo.providers;

import com.example.demo.core.CustomDataBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * CustomDataBaseRepository 구현체
 *
 * @author Eddy Kim
 */
@Repository
public class SimpleCustomDataBaseProvider<T, ID> implements CustomDataBaseRepository<T, ID> {

    private Set<T> hashSet = new HashSet<>();

    public List<T> findAll() {
        return new ArrayList<>(hashSet);
    }

    public Optional<T> findById(ID id) {
        return null;
    }

    public <S extends T> S save(S entity) {
        hashSet.add(entity);
        return entity;
    }

    public T findByName(String name) {
        return null;
    }






    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    public boolean existsById(ID id) {
        return false;
    }


    public Iterable<T> findAllById(Iterable<ID> ids) {
        return null;
    }


    public long count() {
        return 0;
    }


    public void deleteById(ID id) {

    }


    public void delete(T entity) {

    }


    public void deleteAll(Iterable<? extends T> entities) {

    }


    public void deleteAll() {

    }
}
