package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoRatingException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<RatingEntity> getRatings() {
        return this.ratingRepository.getRatingDao().findAll();
    }

    public void putARating(Rating rating)  {
        this.ratingRepository.putARating(rating);
    }

    public double getAnimeGlobalRating(Long animeid) {
        return this.ratingRepository.getAnimeGlobalRating(animeid);
    }

    public Long getCurrentUserRatingForASelectAnime(String currentUserLogin, Long animeid) throws NoUserFoundException {
        if (this.ratingRepository.getCurrentUserRatingForASelectAnime(currentUserLogin, animeid).isPresent()) {
            return this.ratingRepository.getCurrentUserRatingForASelectAnime(currentUserLogin, animeid).get().getRate();
        } else {
            return 666L;
        }
    }
}
