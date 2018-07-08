package spring.data.redis.web.entrypointers.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.data.redis.web.core.entity.Job;
import spring.data.redis.web.core.entity.Person;
import spring.data.redis.web.core.usecase.PersonUseCase;

import java.util.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonUseCase personUseCase;

    public PersonController(PersonUseCase personUseCase) {
        this.personUseCase = personUseCase;
    }

    @GetMapping
    public List<Person> findAll() {
        return personUseCase.findAll();
    }

    @GetMapping("/{name}")
    public List<Person> findByName(@PathVariable String name){
        return personUseCase.findByName(name);
    }

    //TODO:PostMapping 을 위한 Request Body 데이터 구현
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save() throws JsonProcessingException {


        Person person = new Person("Eddy", 1985);
        person.setJob(Arrays.asList(new Job("래퍼")));
        personUseCase.save(person);


        /*
        Person person = new Person("임시완", 1988);
        LinkedHashMap<String, Object> discription = new LinkedHashMap<>();
        discription.put("좌우명", "할 수 있다.");
        discription.put("최근드라마", "미생");
        person.setDescription(discription);
        personUseCase.save(person);


        person = new Person("이진우", 1984);
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

        personUseCase.save(person);
    }




}
