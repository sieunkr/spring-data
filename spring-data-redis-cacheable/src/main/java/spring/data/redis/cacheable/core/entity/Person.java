package spring.data.redis.cacheable.core.entity;

import lombok.Data;

import java.util.List;

@Data
public class Person {

    private String name;
    private Integer born;
    private List<String> jobs;

    public Person(String name, Integer born, List<String> jobs){
        this.name = name;
        this.born = born;
        this.jobs = jobs;
    }

}
