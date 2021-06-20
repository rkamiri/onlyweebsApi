package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Article;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ArticleResearch;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.ArticleService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<Article>> getArticles() {
        CacheControl cacheControl = CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate();
        List<Article> content = this.articleService.findAllArticles();
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable long id) {
        return this.articleService.getArticle(id);
    }

    @PostMapping
    public Long postArticle(@RequestBody Article article) throws NotFoundException {
        if (UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }")) {
            return this.articleService.postArticle(article);
        } else {
            return null;
        }
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<Article>> getArticlesByPage(@PathVariable int page) {
        List<Article> content = this.articleService.getArticlesByPage(page);
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).body(content);
    }

    @PostMapping("/research/page/{page}")
    public ResponseEntity<List<Article>> getArticlesByCategoryId(@RequestBody ArticleResearch articleResearch, @PathVariable int page) {
        List<Article> content = this.articleService.getArticlesByCategoryId(page, articleResearch.title, articleResearch.categoryId);
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).body(content);
    }

    @GetMapping("similar/article_id/{articleId}/category/{category}")
    public ResponseEntity<List<Article>> getSimilarArticles(@PathVariable long category, @PathVariable long articleId) {
        CacheControl cacheControl = CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate();
        List<Article> content = this.articleService.getSimilarArticles(category, articleId);
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }
}
