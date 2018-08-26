package spring.data.arangodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.data.arangodb.entity.ActorsRepository;
import spring.data.arangodb.entity.Movies;
import spring.data.arangodb.entity.Videos;
import spring.data.arangodb.entity.VideosRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class VideosController {

    @Autowired
    private VideosRepository videosRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @GetMapping("/videos/test")
    public Collection<Videos> list(@RequestParam(name="movie") String movieName) {

        return StreamSupport.stream(videosRepository.findByMoviesName(movieName).spliterator(), true)
                .collect(Collectors.toList());
    }

    @GetMapping("/videos/test2")
    public Collection<Videos> listByActor(@RequestParam(name="actor") String actorName) {

        //TODO:리팩토링 해야할 듯;
        Collection<Videos> videos = new ArrayList<>();

        actorsRepository.findByName(actorName).get().getMovies().stream().forEach(ar -> {
            StreamSupport.stream(videosRepository.findByMoviesName(ar.getName()).spliterator(), true).forEach(az -> {
                    videos.add(az);
            });
        });
        return videos;
    }
}
