package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Article;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ArticleRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleEntity> findAllArticles() {
        return this.articleRepository.findAllArticles();
    }

    public Article getArticle(long id) {
        return this.articleRepository.getArticle(id);
    }

    public Long postArticle(Article article) throws NotFoundException {
        return this.articleRepository.postArticle(article);
    }

    public List<ArticleEntity> getArticlesByCategoryId (int page, Long categoryId){
        return this.articleRepository.getArticlesByCategoryId(page, categoryId);
    }

    public List<ArticleEntity> getArticlesByPage (int page){
        return this.articleRepository.getArticlesByPage(page);
    }
}
