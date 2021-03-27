package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoRatingException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<RatingEntity> getRatings() {
        return this.ratingService.getRatings();
    }

    @PutMapping
    public void putARating(@RequestBody Rating rating) {
        this.ratingService.putARating(rating);
    }

    @GetMapping("/user/{id}")
    public Long getCurrentUserRatingForASelectAnime(@PathVariable(value = "id")Long animeid) throws NoRatingException, NoUserFoundException {
        return this.ratingService.getCurrentUserRatingForASelectAnime(UserController.getCurrentUserLogin(), animeid);
    }

    @GetMapping("/{id}")
    public double getAnimeGlobalRating(@PathVariable(value = "id")Long animeid) {
        return this.ratingService.getAnimeGlobalRating(animeid);
    }
}
