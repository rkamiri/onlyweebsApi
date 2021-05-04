package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleRepository {

    private final ArticleDao articleDao;

    @Autowired
    public ArticleRepository(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public List<Article> findAllArticles() {
        List<ArticleEntity> entities = this.articleDao.findAll();
        return entities.stream().map(Article::new).collect(Collectors.toList());
    }

    public Article getArticle(long id) {
        return new Article(this.articleDao.getOne(id));
    }
}
