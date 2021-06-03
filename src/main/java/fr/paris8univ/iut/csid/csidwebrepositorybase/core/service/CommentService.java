package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ArticleDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.CommentDao;
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

    public CommentService(CommentRepository commentRepository, CommentDao commentDao, ArticleDao articleDao, AnimeDao animeDao) {
        this.commentRepository = commentRepository;
        this.commentDao = commentDao;
        this.articleDao = articleDao;
        this.animeDao = animeDao;
    }

    public void putComment(Comment comment) throws NotFoundException {
        this.commentRepository.putComment(comment);
    }

    public List<Comment> getComments(long id, boolean isAnime) {
        List<CommentEntity> commentEntities;
        List<Comment> commentList = new ArrayList<>();

        if (isAnime) {
            commentEntities = this.commentDao.findCommentEntitiesByAnimeEntity(animeDao.findById(id).orElseThrow());
        } else {
            commentEntities = this.commentDao.findCommentEntitiesByArticleEntity(articleDao.findById(id).orElseThrow());
        }
        for (CommentEntity commentEntity : commentEntities) {
            commentList.add(new Comment(commentEntity));
        }

        return commentList;
    }

/*    public String getCurrentUserCommentForASelectAnime(String currentUserLogin, long animeId) {
        if (this.commentRepository.getCurrentUserAnimeCommentForASelectAnime(currentUserLogin, animeId).equals(Optional.empty())) {
            return "666";
        } else {
            return this.commentRepository.getCurrentUserAnimeCommentForASelectAnime(currentUserLogin, animeId).orElseThrow().getBody();
        }
    }*/

    public void deleteComment(String currentUserLogin, long animeId, boolean isAnimeComment) {
        this.commentRepository.deleteComment(currentUserLogin, animeId, isAnimeComment);
    }
}
