package com.example.demo.core.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "USE")
    private List<Stack> stacks = new ArrayList<>();

    //TODO:무한 Depth 되는 현상 처리
    @Relationship(type = "FOLLOW")
    private List<Person> follows = new ArrayList<>();

}
