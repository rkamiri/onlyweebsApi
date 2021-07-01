package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.RatingRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.RatingDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.RatingId;
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

    public List<RatingDto> getRatings() {
        return this.ratingRepository.findAll().stream().map(RatingDto::new).collect(Collectors.toList());
    }

    public void putARating(RatingDto ratingDto) {
        this.ratingRepository.save(new RatingEntity(ratingDto.getUserId(), ratingDto.getAnimeId(), ratingDto.getRate()));
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

    public Optional<RatingDto> getCurrentUserRatingForASelectAnime(String currentUserLogin, Long animeId) {
        if (usersRepository.findByUsername(currentUserLogin).isPresent()) {
            return this.ratingRepository.findById(new RatingId(usersRepository.findByUsername(currentUserLogin).get().getId(), animeId)).map(RatingDto::new);
        } else {
            return Optional.empty();
        }
    }
}
