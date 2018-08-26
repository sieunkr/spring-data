package spring.data.arangodb.entity;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import org.springframework.data.annotation.Id;

@Edge("videoGroup")
public class VideoGroup {
    @Id
    private String id;

    @From
    private Videos videos;

    @To
    private Movies movies;

    public VideoGroup(final Videos videos, final Movies movies){
        super();
        this.videos = videos;
        this.movies = movies;
    }
}
