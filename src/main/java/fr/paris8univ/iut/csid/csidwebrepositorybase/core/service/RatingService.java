package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.RatingRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Rating> getRatings() {
        return this.ratingRepository.getRatingDao().findAll().stream().map(Rating::new).collect(Collectors.toList());
    }

    public void putARating(Rating rating)  {
        this.ratingRepository.putARating(rating);
    }

    public double getAnimeGlobalRating(Long animeid) {
        return this.ratingRepository.getAnimeGlobalRating(animeid);
    }

    public Long getCurrentUserRatingForASelectAnime(String currentUserLogin, Long animeid) {
        if (this.ratingRepository.getCurrentUserRatingForASelectAnime(currentUserLogin, animeid).equals(Optional.empty())) {
            return 666L;
        } else {
            return this.ratingRepository.getCurrentUserRatingForASelectAnime(currentUserLogin, animeid).orElseThrow().getRate();
        }
    }
}
