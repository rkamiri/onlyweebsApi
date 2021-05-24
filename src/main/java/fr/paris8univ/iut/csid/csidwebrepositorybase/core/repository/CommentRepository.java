package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller.UserController;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.CommentDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class CommentRepository {

    private final CommentDao commentDao;
    private final AnimeDao animeDao;
    private final ArticleDao articleDao;
    public final UsersRepository usersRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public CommentRepository(CommentDao commentDao, AnimeDao animeDao, ArticleDao articleDao, UsersRepository usersRepository) {
        this.commentDao = commentDao;
        this.animeDao = animeDao;
        this.articleDao = articleDao;
        this.usersRepository = usersRepository;
    }

    public void putComment(Comment comment) {
        LocalDateTime now = LocalDateTime.now();
        UsersEntity user = this.usersRepository.findUserEntityByUsername(UserController.getCurrentUserLogin());
        List<CommentEntity> commentEntities;
        boolean isAnimeComment;
        if (comment.getArticleEntity()==null) {
            AnimeEntity animeEntity = comment.getAnimeEntity();
            commentEntities = this.commentDao.findCommentEntitiesByAnimeEntityAndUsersEntity(animeEntity, user);
            isAnimeComment = true;
        } else {
            ArticleEntity articleEntity = comment.getArticleEntity();
            commentEntities = this.commentDao.findCommentEntitiesByArticleEntityAndUsersEntity(articleEntity, user);
            isAnimeComment = false;
        }
        if (commentEntities.size() > 0) {
            commentEntities.get(0).setBody(comment.getBody());
            commentEntities.get(0).setDate(dtf.format(now));
            commentDao.save(commentEntities.get(0));
        } else {
            CommentEntity commentEntity = new CommentEntity(user, comment.getBody(), dtf.format(now));
            if (isAnimeComment) {
                commentEntity.setAnimeEntity(comment.getAnimeEntity());
            } else {
                commentEntity.setArticleEntity(comment.getArticleEntity());
            }
            this.commentDao.save(commentEntity);
        }
    }

/*    public Optional<Comment> getCurrentUserAnimeCommentForASelectAnime(String currentUserLogin, Long animeId) {
        if (usersRepository.findByUsername(currentUserLogin).isPresent()) {
            return this.commentDao.findById(new AnimeCommentId(usersRepository.findByUsername(currentUserLogin).get().getId(), animeId)).map(Comment::new);
        } else {
            return Optional.empty();
        }
    }*/

    @Transactional
    public void deleteComment(String currentUserLogin, long id, boolean isAnimeComment) {
        UsersEntity user = this.usersRepository.findUserEntityByUsername(currentUserLogin);
        if (isAnimeComment) {
            this.commentDao.deleteCommentEntityByAnimeEntityAndUsersEntity(this.animeDao.getOne(id), user);
        } else {
            this.commentDao.deleteCommentEntityByArticleEntityAndUsersEntity(this.articleDao.getOne(id), user);
        }
    }
}
