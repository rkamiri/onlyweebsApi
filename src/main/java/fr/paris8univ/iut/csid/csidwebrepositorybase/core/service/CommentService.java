package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Comment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void putAComment(Comment comment)  {
        this.commentRepository.putAComment(comment);
    }

    public List<Comment> getAnimeComments(long animeId) {
        List<CommentEntity> commentEntities = this.commentRepository.getAllCommentsByAnimeId(animeId);
        List<Comment> commentList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            commentList.add(new Comment(commentEntity));
        }
        return commentList;
    }

    public String getCurrentUserCommentForASelectAnime(String currentUserLogin, long animeId) {
        if (this.commentRepository.getCurrentUserAnimeCommentForASelectAnime(currentUserLogin, animeId).equals(Optional.empty())) {
            return "666";
        } else {
            return this.commentRepository.getCurrentUserAnimeCommentForASelectAnime(currentUserLogin, animeId).orElseThrow().getBody();
        }
    }

    public void deleteComment(String currentUserLogin, long animeId) {
        this.commentRepository.deleteComment(currentUserLogin, animeId);
    }
}
