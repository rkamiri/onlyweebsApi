package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ArticleCategoriesDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.ArticleCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/article-categories")
public class ArticlesCategoriesController {

    private final ArticleCategoriesService articleCategoriesService;

    @Autowired
    public ArticlesCategoriesController(ArticleCategoriesService articleCategoriesService) {
        this.articleCategoriesService = articleCategoriesService;
    }

    @GetMapping("/all")
    public List<ArticleCategoriesDto> getCategories() {
        return this.articleCategoriesService.findAll();
    }
}
