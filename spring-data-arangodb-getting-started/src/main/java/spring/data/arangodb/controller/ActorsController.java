package spring.data.arangodb.controller;

import com.arangodb.springframework.core.ArangoOperations;
import com.arangodb.springframework.repository.SimpleArangoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.data.arangodb.entity.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ActorsController {

    @Autowired
    private ArangoOperations arangoOperations;


    @Autowired
    private ActorsRepository actorsRepository;


    @GetMapping("/actors")
    public Page<Actors> list(Pageable pageable) {

        Page<Actors> actors = actorsRepository.findAll(pageable);
        return actors;
    }

    /*
    @GetMapping("/actors")
    public Page<Actors> list(Pageable pageable) {

        Page<Actors> actors = (new SimpleArangoRepository(arangoOperations, Actors.class).findAll(pageable));
        return actors;
    }
    */

    /*
    @GetMapping("/actors")
    public Collection<Actors> listActorsByMovie(@RequestParam(name="movie") String movieName) {

        return StreamSupport.stream(actorsRepository.findByMoviesName(movieName).spliterator(), true)
                .collect(Collectors.toList());

    }
    */


    @GetMapping("/movies")
    public Collection<Movies> listByActedin(@RequestParam(name="name") String name) {
        return actorsRepository.findByName(name).get().getMovies();
    }


}
