package spring.data.redis.cacheable.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.data.redis.cacheable.core.entity.Person;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersonUseCase {

    private final PersonInterface personInterface;

    public Map findByNid(String nid){
        return personInterface.findByNid(nid);
    }
}
