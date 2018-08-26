package spring.data.arangodb.entity;

import com.arangodb.springframework.repository.ArangoRepository;

import java.util.Optional;

public interface ActorsRepository extends ArangoRepository<Actors> {
    Optional<Actors> findByName(String name);

    Iterable<Actors> findByMoviesName(String name);

}
