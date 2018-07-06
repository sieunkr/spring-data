package spring.data.redis.web.core.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

//TODO: Lombok @Data 적용
@RedisHash("persons")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private @Id
    String id;

    private @Indexed
    String name;

    private Integer birthyear;

    private List<Job> job;

    private LinkedHashMap<String, Object> description;

    public Person(String name, Integer birthyear){
        this.name = name;
        this.birthyear = birthyear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Integer birthyear) {
        this.birthyear = birthyear;
    }

    public List<Job> getJob() {
        return job;
    }

    public void setJob(List<Job> job) {
        this.job = job;
    }



    public LinkedHashMap<String, Object> getDescription() {
        return description;
    }

    public void setDescription(LinkedHashMap<String, Object> description) {
        this.description = description;
    }
}

