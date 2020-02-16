package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/color")
public class ColorController {

    private final ColorService colorService;
    private final static String REDIS_KEY_PRIFIX = "color:";

    @GetMapping("/performance-test")
    public List<ColorDTO> testPerformance() {

        return colorService.mget(getStub());
    }

    @GetMapping("/init")
    public String init() {

        colorService.init();
        return "ok";
    }

    private List<String> getStub() {
        return Stream.of(
                "1", "2", "5", "7", "10", "11", "13", "123", "1100", "1205",
                "1277", "1455", "1500", "1647", "1717", "1818", "1919", "1946", "1947", "1948",
                "11277", "11455", "11500", "11647", "11717", "11818", "11919", "11946", "11947", "11948",
                "21278", "21458", "21508", "21648", "21718", "21819", "21918", "21945", "21944", "21942",
                "31277", "31455", "31500", "31647", "31717", "31818", "31919", "31946", "31947", "31948"
        ).map(id -> REDIS_KEY_PRIFIX + id).collect(Collectors.toList());
    }
}