package com.example.demo;

import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Map;

import static org.springframework.data.domain.Sort.Order.desc;

@Repository
public class CustomRepository {

    private final DatabaseClient databaseClient;

    public CustomRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    Flux<Coffee> findByAll(){

        return databaseClient.execute()
                .sql("SELECT * FROM COFFEES")
                .as(Coffee.class)
                .fetch()
                .all();

        /*
        return databaseClient.select()
                .from("coffees")
                .orderBy(Sort.by(desc("id")))
                .as(Coffee.class)
                .fetch()
                .all();
        */

    }
    
    //TODO: name 으로 조회
    Flux<Coffee> findByName(String name){

        return databaseClient.select()
                .from("coffees")
                .orderBy(Sort.by(desc("id")))
                .as(Coffee.class)
                .fetch()
                .all();
    }
}
