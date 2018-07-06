package spring.data.redis.web.entrypointers.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.data.redis.web.core.entity.Job;
import spring.data.redis.web.core.entity.Person;
import spring.data.redis.web.core.repo.PersonRepository;

import java.util.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> findAll() {

        return (List<Person>) personRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<Person> findByName(@PathVariable String name){

        return personRepository.findByName(name);
    }

    //TODO:PostMapping 을 위한 Request Body 데이터 구현
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save() throws JsonProcessingException {

        Person person = new Person("Eddy", 1981);
        person.setJob(Arrays.asList(new Job("개발자")));
        personRepository.save(person);

        /*
        Person person = new Person("임시완", 1988);
        LinkedHashMap<String, Object> discription = new LinkedHashMap<>();
        discription.put("좌우명", "할 수 있다.");
        discription.put("최근드라마", "미생");
        person.setDescription(discription);
        personRepository.save(person);
        */

        /*
        Person person = new Person("이진우", 1984);
        LinkedHashMap<String, Object> description = new LinkedHashMap<>();
        Map<String, String> school = new HashMap<>();
        school.put("학사","숭실대학교");
        school.put("석사","숭실대학교");
        String schoolByJson = new ObjectMapper().writeValueAsString(school);
        description.put("회사", "더좋은회사커뮤니케이션즈");
        description.put("학교", schoolByJson);
        description.put("age", 35);
        person.setDescription(description);
        */

        personRepository.save(person);
    }




}
