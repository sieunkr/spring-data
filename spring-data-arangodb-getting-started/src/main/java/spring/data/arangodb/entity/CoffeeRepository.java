package spring.data.arangodb.entity;

import com.arangodb.springframework.repository.ArangoRepository;

public interface CoffeeRepository extends ArangoRepository<Coffee> {

    Iterable<Coffee> findByChildsAgeBetween(int lowerBound, int upperBound);

    Coffee findByName(String name);

    Iterable<Coffee> findByPrice(Integer price);

}
