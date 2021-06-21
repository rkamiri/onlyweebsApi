package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleCategoriesDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ArticleCategories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleCategoriesService {

    private final ArticleCategoriesDao articleCategoriesDao;

    public ArticleCategoriesService(ArticleCategoriesDao articleCategoriesDao) {
        this.articleCategoriesDao = articleCategoriesDao;
    }

    public List<ArticleCategories> findAll(){
        return this.articleCategoriesDao.findAll().stream().map(ArticleCategories::new).collect(Collectors.toList());
    }
}
