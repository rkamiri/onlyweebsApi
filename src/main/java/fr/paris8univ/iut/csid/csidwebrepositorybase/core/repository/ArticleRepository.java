package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
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

    public Long postArticle(Article article) {
        this.articleDao.save(
                new ArticleEntity(
                        article.getTitle(),
                        article.getBody(),
                        article.getCreated_at(),
                        article.getAuthor(),
                        article.getCover()
                ));
        return this.getLastArticleId();
    }

    public Long getLastArticleId() {
        List<ArticleEntity> ael = this.articleDao.findAll();
        if (ael.size() > 0) {
            Long id = 1L;
            for (ArticleEntity ae : ael) {
                if (ae.getId() >= id) id = ae.getId();
            }
            return this.articleDao.getOne(id).getId();
        } else {
            return 0L;
        }
    }
}
