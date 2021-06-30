package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.RatingService;
import javassist.NotFoundException;
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
    public List<Rating> getRatings() {
        return this.ratingService.getRatings();
    }

    @PutMapping
    public Long putARating(@RequestBody Rating rating) {
        this.ratingService.putARating(rating);
        return rating.getRate();
    }

    @GetMapping("/user/{id}")
    public Long getCurrentUserRatingForASelectAnime(@PathVariable(value = "id")Long animeId) throws NotFoundException {
        return this.ratingService.getUserRatingForAnAnime(UserController.getCurrentUserLogin(), animeId);
    }

    @GetMapping("/{id}")
    public double getAnimeGlobalRating(@PathVariable(value = "id")Long animeId) {
        return this.ratingService.getAnimeGlobalRating(animeId);
    }
}
