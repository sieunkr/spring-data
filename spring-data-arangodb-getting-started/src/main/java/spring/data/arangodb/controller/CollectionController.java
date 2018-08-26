package spring.data.arangodb.controller;

import com.arangodb.springframework.core.ArangoOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.data.arangodb.entity.Coffee;

import java.util.List;

@RestController
public class CollectionController {

    @Autowired
    private ArangoOperations arangoOperations;

    @GetMapping("/collections")
    public List<?> list(){


        System.out.println("ㅇㅇㅇ");
        //System.out.println(arangoOperations.findAll());



        return null;
    }
}
