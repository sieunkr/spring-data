package spring.data.redis.web.entrypointers.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.data.redis.web.core.entity.Movie;
import spring.data.redis.web.core.repository.MovieRepository;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //TODO:페이징
    @GetMapping
    public List<Movie> findAll(Pageable pageable) {
        return (List<Movie>) movieRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<Movie> findByName(@PathVariable String name){
        return movieRepository.findByName(name);
    }

}
