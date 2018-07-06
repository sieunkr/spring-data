package spring.data.redis.web.core.entity;

import lombok.Data;

//TODO:Lombok 적용
public class Job {

    private String name;

    public Job(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

