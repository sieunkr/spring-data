package spring.data.arangodb.entity;

import com.arangodb.springframework.annotation.Document;

@Document("movies")
public class Movies {
    private String name;

    public String getName() {
        return name;
    }
}
