package spring.data.redis.cacheable.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import spring.data.redis.cacheable.core.entity.Person;
import spring.data.redis.cacheable.core.repository.PersonRepository;
import spring.data.redis.cacheable.core.usecase.PersonInterface;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class dataStorageProvider implements PersonInterface {

    private final PersonRepository personRepository;

    @Override
    @Cacheable(value = "person", key = "#nid")
    public Map findByNid(String nid) {
        return personRepository.findByNid(nid);
    }

    /*
    @PostConstruct
    public void init(){
        Person person01 = new Person("eddy", 1981, Arrays.asList("developer","tester"));
        ObjectMapper oMapper = new ObjectMapper();
        Map map = oMapper.convertValue(person01, Map.class);
        personRepository.getPersonStorage().put("20180826", map);
    }
    */

}
