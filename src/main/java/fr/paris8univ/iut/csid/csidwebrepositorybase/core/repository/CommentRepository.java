package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller.UserController;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.CommentDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ListsDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Comment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import javassist.NotFoundException;
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
    private final ListsDao listsDao;
    public final UsersRepository usersRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public CommentRepository(CommentDao commentDao, AnimeDao animeDao, ArticleDao articleDao, ListsDao listsDao, UsersRepository usersRepository) {
        this.commentDao = commentDao;
        this.animeDao = animeDao;
        this.articleDao = articleDao;
        this.listsDao = listsDao;
        this.usersRepository = usersRepository;
    }

    public void putComment(Comment comment) throws NotFoundException {
        LocalDateTime now = LocalDateTime.now();
        UsersEntity user = this.usersRepository.findUserEntityByUsername(UserController.getCurrentUserLogin());
        List<CommentEntity> commentEntities;
        int whichTypeOfComment;
        if (comment.getArticleEntity() == null && comment.getListsEntity() == null) { // anime comment
            AnimeEntity animeEntity = comment.getAnimeEntity();
            commentEntities = this.commentDao.findCommentEntitiesByAnimeEntityAndUsersEntity(animeEntity, user);
            whichTypeOfComment = 0;
        } else if (comment.getAnimeEntity() == null && comment.getListsEntity() == null) { // article comment
            ArticleEntity articleEntity = comment.getArticleEntity();
            commentEntities = this.commentDao.findCommentEntitiesByArticleEntityAndUsersEntity(articleEntity, user);
            whichTypeOfComment = 1;
        } else { // list comment
            ListsEntity listsEntity = comment.getListsEntity();
            commentEntities = this.commentDao.findCommentEntitiesByListsEntityAndUsersEntity(listsEntity, user);
            whichTypeOfComment = 2;
        }
        if (commentEntities.size() > 0) {
            commentEntities.get(0).setBody(comment.getBody());
            commentEntities.get(0).setDate(dtf.format(now));
            commentDao.save(commentEntities.get(0));
        } else {
            CommentEntity commentEntity = new CommentEntity(user, comment.getBody(), dtf.format(now));
            if (whichTypeOfComment == 0) {
                commentEntity.setAnimeEntity(comment.getAnimeEntity());
            } else if (whichTypeOfComment == 1) {
                commentEntity.setArticleEntity(comment.getArticleEntity());
            } else {
                commentEntity.setListsEntity(comment.getListsEntity());
            }
            this.commentDao.save(commentEntity);
        }
    }

    @Transactional
    public void deleteComment(UsersEntity owner, long id, int type) {
        if (type==0) {
            this.commentDao.deleteCommentEntityByAnimeEntityAndUsersEntity(this.animeDao.getOne(id), owner);
        } else if (type == 1){
            this.commentDao.deleteCommentEntityByArticleEntityAndUsersEntity(this.articleDao.getOne(id), owner);
        } else {
            this.commentDao.deleteCommentEntityByListsEntityAndUsersEntity(this.listsDao.getOne(id), owner);

        }
    }
}
