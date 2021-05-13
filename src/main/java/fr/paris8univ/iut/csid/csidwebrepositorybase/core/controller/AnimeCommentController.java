package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import com.fasterxml.jackson.databind.JsonSerializable;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoRatingException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeComment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.AnimeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/anime-comment")
public class AnimeCommentController {

    private final AnimeCommentService animeCommentService;

    @Autowired
    public AnimeCommentController(AnimeCommentService animeCommentService) {
        this.animeCommentService = animeCommentService;
    }

    @GetMapping("/{id}")
    public List<AnimeCommentEntity> getCommentsForAnime(@PathVariable(value = "id")long animeId) {return this.animeCommentService.getAnimeComments(animeId);}

    @PutMapping
    public void putAComment(@RequestBody AnimeComment comment) {
        this.animeCommentService.putAComment(comment);
    }

    @GetMapping("/user/{id}")
    public String getCurrentUserCommentForASelectAnime(@PathVariable(value = "id")long animeId) {
        return this.animeCommentService.getCurrentUserCommentForASelectAnime(UserController.getCurrentUserLogin(), animeId);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable long id) {
        this.animeCommentService.deleteComment(UserController.getCurrentUserLogin(), id);
    }
}
