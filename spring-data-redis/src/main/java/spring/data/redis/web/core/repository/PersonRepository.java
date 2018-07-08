package spring.data.redis.web.core.repository;

import org.springframework.data.repository.CrudRepository;
import spring.data.redis.web.core.entity.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, String> {

    List<Person> findByName(String name);
}