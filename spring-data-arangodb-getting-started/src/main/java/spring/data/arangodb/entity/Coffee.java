package spring.data.arangodb.entity;

import com.arangodb.springframework.annotation.Document;

@Document
public class Coffee {

    private String name;

    private Integer price;

    public Coffee(String name, Integer price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
