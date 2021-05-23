package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Comment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/anime-comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public List<Comment> getCommentsForAnime(@PathVariable(value = "id")long animeId) {
        return this.commentService.getAnimeComments(animeId);
    }

    @PutMapping
    public void putAComment(@RequestBody Comment comment) {
        this.commentService.putAComment(comment);
    }

    @GetMapping("/user/{id}")
    public String getCurrentUserCommentForASelectAnime(@PathVariable(value = "id")long animeId) {
        return this.commentService.getCurrentUserCommentForASelectAnime(UserController.getCurrentUserLogin(), animeId);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        this.commentService.deleteComment(UserController.getCurrentUserLogin(), id);
    }
}
