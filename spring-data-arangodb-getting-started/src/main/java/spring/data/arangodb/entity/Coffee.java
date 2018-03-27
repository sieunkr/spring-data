package spring.data.arangodb.entity;

import com.arangodb.springframework.annotation.Document;
import org.springframework.data.annotation.Id;

@Document
public class Coffee {

    @Id
    private String name;

    private Integer price;

    public Coffee(String name, Integer price){
        this.name = name;
        this.price = price;
    }
}
