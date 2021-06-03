package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleCategoriesDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleCategoriesEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCategoriesService {

    private final ArticleCategoriesDao articleCategoriesDao;

    public ArticleCategoriesService(ArticleCategoriesDao articleCategoriesDao) {
        this.articleCategoriesDao = articleCategoriesDao;
    }

    public List<ArticleCategoriesEntity> findAll(){
        return this.articleCategoriesDao.findAll();
    }
}
