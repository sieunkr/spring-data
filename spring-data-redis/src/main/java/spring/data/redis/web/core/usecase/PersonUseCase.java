package spring.data.redis.web.core.usecase;

import org.springframework.stereotype.Service;
import spring.data.redis.web.core.entity.Person;

import java.util.List;

/*

UC-1.액터(사용자 또는 편집자)는 이름으로 Person 정보를 조회한다.
UC-2.액터(사용자 또는 편집자)는 Person 모든 정보를 조회한다.
UC-3.액터(사용자 또는 편집자)는 신규 Person 정보를 저장한다.
 */

@Service
public class PersonUseCase {

    private final Personal personal;

    public PersonUseCase(Personal personal) {
        this.personal = personal;
    }

    public List<Person> findByName(String name){
        return personal.findByName(name);
    }

    public List<Person> findAll(){
        return personal.findAll();
    }

    public void save(Person person){
        personal.save(person);
    }
}
