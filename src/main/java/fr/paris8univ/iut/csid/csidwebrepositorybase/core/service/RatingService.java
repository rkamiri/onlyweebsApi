package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.RatingRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.RatingId;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    public final UsersRepository usersRepository;

    public RatingService(RatingRepository ratingRepository, UsersRepository usersRepository) {
        this.ratingRepository = ratingRepository;
        this.usersRepository = usersRepository;
    }

    public List<Rating> getRatings() {
        return this.ratingRepository.findAll().stream().map(Rating::new).collect(Collectors.toList());
    }

    public void putARating(Rating rating) {
        this.ratingRepository.save(new RatingEntity(rating.getUserId(), rating.getAnimeId(), rating.getRate()));
    }

    public double getAnimeGlobalRating(Long animeId) {
        List<RatingEntity> ratings = this.ratingRepository.getRatingEntitiesByAnimeId(animeId);
        int div = ratings.size();
        double add = 0;
        for (RatingEntity entity : ratings) {
            add += entity.getRate();
        }
        return add / div;
    }

    public Long getUserRatingForAnAnime(String currentUserLogin, Long animeId) {
        if (this.getCurrentUserRatingForASelectAnime(currentUserLogin, animeId).equals(Optional.empty())) {
            return 666L;
        } else {
            return this.getCurrentUserRatingForASelectAnime(currentUserLogin, animeId).orElseThrow().getRate();
        }
    }

    public Optional<Rating> getCurrentUserRatingForASelectAnime(String currentUserLogin, Long animeId) {
        if (usersRepository.findByUsername(currentUserLogin).isPresent()) {
            return this.ratingRepository.findById(new RatingId(usersRepository.findByUsername(currentUserLogin).get().getId(), animeId)).map(Rating::new);
        } else {
            return Optional.empty();
        }
    }
}
