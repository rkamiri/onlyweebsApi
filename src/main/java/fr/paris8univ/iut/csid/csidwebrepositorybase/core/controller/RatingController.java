package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.PasRatingException;
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
    public Long getCurrentUserRatingForASelectAnime(@PathVariable(value = "id")Long animeid) throws PasRatingException, NoUserFoundException {
        return this.ratingService.getCurrentUserRatingForASelectAnime(UserController.getCurrentUserLogin(), animeid);
    }

    @GetMapping("/{id}")
    public double getAnimeGlobalRating(@PathVariable(value = "id")Long animeid) {
        return this.ratingService.getAnimeGlobalRating(animeid);
    }
}
