package spring.data.redis.cacheable.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Person implements Serializable {

    private String name;
    private Integer born;
    private List<String> jobs;

    public Person(String name, Integer born, List<String> jobs){
        this.name = name;
        this.born = born;
        this.jobs = jobs;
    }

}
