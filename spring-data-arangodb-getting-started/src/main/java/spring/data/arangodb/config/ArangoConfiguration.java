package spring.data.arangodb.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.AbstractArangoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = { "spring.data.arangodb.entity" })
public class ArangoConfiguration extends AbstractArangoConfiguration {

    @Override
    public ArangoDB.Builder arango() {
        ArangoDB.Builder arango = new ArangoDB.Builder()
                .host("119.205.221.42")
                .port(8529)
                .user("root")
                .password("3792154");
        return arango;

    }

    @Override
    public String database() {
        // Name of the database to be used
        return "Cafe";
    }
}
