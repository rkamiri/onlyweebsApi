package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoRatingException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeComment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.AnimeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/animeComment")
public class AnimeCommentController {

    private final AnimeCommentService animeCommentService;

    @Autowired
    public AnimeCommentController(AnimeCommentService animeCommentService) {
        this.animeCommentService = animeCommentService;
    }

    @GetMapping
    public List<AnimeCommentEntity> getComments() {
        return this.animeCommentService.getComments();
    }

    @PutMapping
    public String putAComment(@RequestBody AnimeComment comment) {
        this.animeCommentService.putAComment(comment);
        return comment.getComment();
    }

    @GetMapping("/user/{id}")
    public String getCurrentUserCommentForASelectAnime(@PathVariable(value = "id")Long animeid) throws NoRatingException, NoUserFoundException {
        return this.animeCommentService.getCurrentUserCommentForASelectAnime(UserController.getCurrentUserLogin(), animeid);
    }
}
