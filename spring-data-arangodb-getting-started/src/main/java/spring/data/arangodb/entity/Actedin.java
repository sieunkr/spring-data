package spring.data.arangodb.entity;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import org.springframework.data.annotation.Id;

@Edge("actedin")
public class Actedin {

    @From
    private Actors actor;

    @To
    private Movies movies;

    public Actedin(final Actors actors, final Movies movies){
        super();
        this.actor = actors;
        this.movies = movies;
    }
}
