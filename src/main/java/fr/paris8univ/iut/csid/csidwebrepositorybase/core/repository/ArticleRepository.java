package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller.UserController;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Article;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Repository
public class ArticleRepository {

    private final ArticleDao articleDao;
    private final UsersRepository usersRepository;
    private final UploadRepository uploadRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public ArticleRepository(ArticleDao articleDao, UsersRepository usersRepository, UploadRepository uploadRepository) {
        this.articleDao = articleDao;
        this.usersRepository = usersRepository;
        this.uploadRepository = uploadRepository;
    }

    public List<ArticleEntity> findAllArticles() {
        return this.articleDao.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Article getArticle(long id) {
        return new Article(this.articleDao.getOne(id));
    }

    public Long postArticle(Article article) throws NotFoundException {
        LocalDateTime now = LocalDateTime.now();
        this.articleDao.save(
                new ArticleEntity(
                        article.getTitle(),
                        article.getBody(),
                        dtf.format(now),
                        this.usersRepository.findUserEntityByUsername(UserController.getCurrentUserLogin()),
                        this.uploadRepository.getLastImage(),
                        article.getCategory()
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
