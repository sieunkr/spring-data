package spring.data.arangodb.entity;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;

import java.util.Collection;

@Document("videos")
public class Videos {
    private String name;

    public String getName() {
        return name;
    }

    @Relations(edges = VideoGroup.class)
    private Collection<Movies> movies;
}
