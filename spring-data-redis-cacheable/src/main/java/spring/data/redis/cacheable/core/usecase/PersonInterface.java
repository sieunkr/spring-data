package spring.data.redis.cacheable.core.usecase;

import spring.data.redis.cacheable.core.entity.Person;

import java.util.Map;

public interface PersonInterface {
    Map findByNid(String nid);
}
