package spring.data.arangodb.entity;

import com.arangodb.springframework.repository.ArangoRepository;

public interface VideosRepository extends ArangoRepository<Videos> {

    Iterable<Videos> findByMoviesName(String name);

}
