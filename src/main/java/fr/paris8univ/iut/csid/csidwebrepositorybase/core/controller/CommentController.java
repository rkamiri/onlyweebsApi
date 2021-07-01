package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.CommentDto;
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
    public List<CommentDto> getCommentsForAnime(@PathVariable(value = "id") long animeId) {
        return this.commentService.getComments(animeId, 0);
    }

    @GetMapping("/article/{id}")
    public List<CommentDto> getCommentsForArticle(@PathVariable(value = "id") long articleId) {
        return this.commentService.getComments(articleId, 1);
    }

    @GetMapping("/list/{id}")
    public List<CommentDto> getCommentsForList(@PathVariable(value = "id") long listId) {
        return this.commentService.getComments(listId, 2);
    }

    @PutMapping
    public void putComment(@RequestBody CommentDto commentDto) throws NotFoundException, NoUserFoundException {
        this.commentService.putComment(commentDto);
    }

    @DeleteMapping("/anime/{animeId}/user/{userId}")
    public void deleteMyAnimeComment(@PathVariable long animeId, @PathVariable long userId) throws NotFoundException {
        if (!UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }") || userId == -667L) {
            this.commentService.deleteCommentAsUser(UserController.getCurrentUserLogin(), animeId, 0);
        } else {
            this.commentService.deleteCommentAsAdmin(userId, animeId, 0);
        }
    }

    @DeleteMapping("/article/{articleId}/user/{userId}")
    public void deleteMyArticleComment(@PathVariable long articleId, @PathVariable long userId) throws NotFoundException {
        if (!UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }") || userId == -667L) {
            this.commentService.deleteCommentAsUser(UserController.getCurrentUserLogin(), articleId, 1);
        } else {
            this.commentService.deleteCommentAsAdmin(userId, articleId, 1);
        }
    }

    @DeleteMapping("/list/{listId}/user/{userId}")
    public void deleteListComment(@PathVariable long listId, @PathVariable long userId) throws NotFoundException {
        if (!UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }") || userId == -667L) {
            this.commentService.deleteCommentAsUser(UserController.getCurrentUserLogin(), listId, 2);
        } else {
            this.commentService.deleteCommentAsAdmin(userId, listId, 2);
        }
    }
}
