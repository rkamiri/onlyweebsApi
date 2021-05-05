package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Article;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getArticles() {
        return this.articleService.findAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable long id) {
        return this.articleService.getArticle(id);
    }

    @PostMapping
    public Long postArticle(@RequestBody Article article) {
        return this.articleService.postArticle(article);
    }
}
