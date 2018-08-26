package spring.data.arangodb.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import org.springframework.data.annotation.Id;

import java.util.Collection;


@Document("actors")
public class Actors {

    @Id
    private String id;

    private String name;

    public String getName() {
        return name;
    }

    @Relations(edges = Actedin.class)
    private Collection<Movies> movies;

    public Collection<Movies> getMovies() {
        return movies;
    }

}
