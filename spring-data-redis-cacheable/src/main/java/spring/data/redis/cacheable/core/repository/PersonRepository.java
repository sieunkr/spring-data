package spring.data.redis.cacheable.core.repository;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class PersonRepository {

    private HashMap<String, Map> personStorage;

    public PersonRepository(){
        personStorage = new HashMap<>();
    }

    public Map findByNid(String nid){
        return personStorage.get(nid);
    }

}
