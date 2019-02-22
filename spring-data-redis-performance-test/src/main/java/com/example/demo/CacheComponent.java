package com.example.demo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheComponent {

    @Cacheable(value = "colors", key = "#name")
    public Color findById(String name){

        try {
            javafx.scene.paint.Color color = javafx.scene.paint.Color.web(name);
            Thread.sleep(100);

            return new Color(name, color.getRed(), color.getBlue(), color.getGreen());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
