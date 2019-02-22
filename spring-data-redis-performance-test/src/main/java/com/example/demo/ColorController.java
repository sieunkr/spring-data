package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/colors")
public class ColorController {

    private final ColorUseCase colorUseCase;

    public ColorController(ColorUseCase colorUseCase) {
        this.colorUseCase = colorUseCase;
    }

    @GetMapping
    public List<Color> findAll(){

        return colorUseCase.findAll();

    }
}
