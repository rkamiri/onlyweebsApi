package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ArticleDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ArticleResearchDto;
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
    public ResponseEntity<List<ArticleDto>> getArticles() {
        List<ArticleDto> content = this.articleService.findAllArticles();
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).body(content);
    }

    @GetMapping("/five")
    public ResponseEntity<List<ArticleDto>> getFiveArticles() {
        List<ArticleDto> content = this.articleService.findFiveArticles();
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).body(content);
    }

    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable long id) {
        return this.articleService.getArticle(id);
    }

    @PostMapping
    public Long postArticle(@RequestBody ArticleDto articleDto) throws NotFoundException, NoUserFoundException {
        if (UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }")) {
            return this.articleService.postArticle(articleDto);
        } else {
            return null;
        }
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<List<ArticleDto>> getArticlesByPage(@PathVariable int page) {
        List<ArticleDto> content = this.articleService.getArticlesByPage(page);
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).body(content);
    }

    @PostMapping("/research/page/{page}")
    public ResponseEntity<List<ArticleDto>> getArticlesByCategoryId(@RequestBody ArticleResearchDto articleResearchDto, @PathVariable int page) {
        List<ArticleDto> content = this.articleService.getArticlesByCategoryId(page, articleResearchDto.title, articleResearchDto.categoryId);
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).body(content);
    }

    @GetMapping("similar/article_id/{articleId}/category/{category}")
    public ResponseEntity<List<ArticleDto>> getSimilarArticles(@PathVariable long category, @PathVariable long articleId) {
        CacheControl cacheControl = CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate();
        List<ArticleDto> content = this.articleService.getSimilarArticles(category, articleId);
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable long id) {
        if (UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }")) {
            this.articleService.deleteArticle(id);
        }
    }
}
