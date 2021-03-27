package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.NoUserFoundException;
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

    public Long getCurrentUserRatingForASelectAnime(String currentUserLogin, Long animeid) throws NoUserFoundException, PasRatingException {
        return this.ratingRepository.getCurrentUserRatingForASelectAnime(currentUserLogin, animeid).orElseThrow(PasRatingException::new).getRate();
    }
}
