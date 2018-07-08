package spring.data.redis.web.dataproviders.database;

import org.springframework.stereotype.Component;
import spring.data.redis.web.core.entity.Person;
import spring.data.redis.web.core.repo.PersonRepository;
import spring.data.redis.web.core.usecase.Personal;

import java.util.List;

@Component
public class PersonDataProvider implements Personal {

    private final PersonRepository personRepository;

    public PersonDataProvider(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }
}
