package spring.data.arangodb.controller;

import com.arangodb.springframework.core.ArangoOperations;
import com.arangodb.springframework.repository.SimpleArangoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import spring.data.arangodb.entity.Coffee;
import spring.data.arangodb.entity.CoffeeRepository;

@RestController
public class CoffeeDefinitionController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    private final ArangoOperations arangoOperations;

    public CoffeeDefinitionController(ArangoOperations arangoOperations){
        this.arangoOperations = arangoOperations;
    }

    /*
    단순 리스트 조회
    @GetMapping("/coffees")
    public List<Coffee> list(){

        List<Coffee> coffees = StreamSupport.stream(coffeeRepository.findAll().spliterator(), true)
                .collect(Collectors.toList());

        return coffees;
    }
    */


    @GetMapping("/coffees")
    public Page<Coffee> list(Pageable pageable){

        Page<Coffee> coffees = coffeeRepository.findAll(pageable);

        Page<Coffee> coffeesBySimple = (new SimpleArangoRepository(arangoOperations, Coffee.class).findAll(pageable));


        return coffees;
    }


    @GetMapping("/coffees/{name}")
    public Coffee find(@PathVariable(value = "name") String name){

        /*
        final Coffee coffee = new Coffee(name, 10);
        return coffeeRepository.findOne(Example.of(coffee)).get();
        */

        return coffeeRepository.findByName(name);
    }


    @PostMapping("/coffees")
    public void save(){

        final Coffee coffee = new Coffee("라떼", 1100);
        coffeeRepository.save(coffee);



    }


}
