package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.CommentDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ListsDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Comment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.CommentRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentDao commentDao;
    private final ArticleDao articleDao;
    private final AnimeDao animeDao;
    private final ListsDao listsDao;

    public CommentService(CommentRepository commentRepository, CommentDao commentDao, ArticleDao articleDao, AnimeDao animeDao, ListsDao listsDao) {
        this.commentRepository = commentRepository;
        this.commentDao = commentDao;
        this.articleDao = articleDao;
        this.animeDao = animeDao;
        this.listsDao = listsDao;
    }

    public void putComment(Comment comment) throws NotFoundException {
        this.commentRepository.putComment(comment);
    }

    public List<Comment> getComments(long objectId, int type) {
        List<CommentEntity> commentEntities;
        List<Comment> commentList = new ArrayList<>();

        if (type == 0) {
            commentEntities = this.commentDao.findCommentEntitiesByAnimeEntity(animeDao.findById(objectId).orElseThrow());
        } else if (type == 1) {
            commentEntities = this.commentDao.findCommentEntitiesByArticleEntity(articleDao.findById(objectId).orElseThrow());
        } else {
            commentEntities = this.commentDao.findCommentEntitiesByListsEntity(listsDao.findById(objectId).orElseThrow());
        }
        for (CommentEntity commentEntity : commentEntities) {
            commentList.add(new Comment(commentEntity));
        }
        return commentList;
    }

    public void deleteComment(String currentUserLogin, long animeId, int type) {
        this.commentRepository.deleteComment(currentUserLogin, animeId, type);
    }
}
