package com.example.demo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "contents")
@Data
public class Content {

    @Id
    private String id;
    private Integer cid;
    private String name;
    private Object data;
    private List<String> keyword;
}
