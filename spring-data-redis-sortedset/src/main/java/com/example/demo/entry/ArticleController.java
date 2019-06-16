package com.example.demo.entry;

import com.example.demo.core.ArticleUseCase;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    private final ArticleUseCase articleUseCase;

    public ArticleController(ArticleUseCase articleUseCase) {
        this.articleUseCase = articleUseCase;
    }

    @PostMapping("/scores")
    public void setScore(@RequestParam(name = "nid") String nid, @RequestParam(name = "articleUrl") String articleUrl){

        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        articleUseCase.setScore(nid, articleUrl, timeStampMillis);
    }

    @GetMapping("/recently-articles")
    public List<String> recentlyArticlesById(@RequestParam(name = "nid") String nid){
        return articleUseCase.recentlyArticlesById(nid);
    }
}
