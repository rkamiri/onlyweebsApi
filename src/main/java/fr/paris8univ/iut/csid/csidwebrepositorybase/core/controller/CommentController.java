package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Comment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.CommentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/anime/{id}")
    public List<Comment> getCommentsForAnime(@PathVariable(value = "id")long animeId) {
        return this.commentService.getComments(animeId, true);
    }

    @GetMapping("/article/{id}")
    public List<Comment> getCommentsForArticle(@PathVariable(value = "id")long animeId) {
        return this.commentService.getComments(animeId, false);
    }

    @PutMapping
    public void putComment(@RequestBody Comment comment) throws NotFoundException {
        this.commentService.putComment(comment);
    }

    @DeleteMapping("/anime/{id}")
    public void deleteAnimeComment(@PathVariable long id) throws NotFoundException {
        this.commentService.deleteComment(UserController.getCurrentUserLogin(), id, true);
    }

    @DeleteMapping("/article/{id}")
    public void deleteArticleComment(@PathVariable long id) throws NotFoundException {
        this.commentService.deleteComment(UserController.getCurrentUserLogin(), id, false);
    }
}
