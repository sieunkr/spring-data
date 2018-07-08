package spring.data.redis.web.core.repository;

import org.springframework.data.repository.CrudRepository;
import spring.data.redis.web.core.entity.Movie;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, String> {

    List<Movie> findByName(String name);
}
