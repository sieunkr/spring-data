package spring.data.redis.web.core.usecase;

import spring.data.redis.web.core.entity.Person;

import java.util.List;

public interface Personal {

    List<Person> findByName(String name);
    List<Person> findAll();
    void save(Person person);
}
