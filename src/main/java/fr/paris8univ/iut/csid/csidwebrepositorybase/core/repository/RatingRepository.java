package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.RatingDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.RatingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RatingRepository {

    private final RatingDao ratingDao;
    public final UsersRepository usersRepository;

    @Autowired
    public RatingRepository(RatingDao ratingDao, UsersRepository usersRepository) {
        this.ratingDao = ratingDao;
        this.usersRepository = usersRepository;
    }

    public void putARating(Rating rating)  {
        this.ratingDao.save(new RatingEntity(rating.getUserId(), rating.getAnimeId(), rating.getRate()));
    }

    public List<RatingEntity> getAllRatingsByAnimeId(Long animeid) {
        List<RatingEntity> ratings = this.ratingDao.findAll();
        ratings.removeIf(entity -> !entity.getAnime_id().equals(animeid));
        return ratings;
    }

    public double getAnimeGlobalRating(Long animeid) {
        List<RatingEntity> ratings = this.getAllRatingsByAnimeId(animeid);
        int div = ratings.size();
        double add = 0;
        for (RatingEntity entity: ratings) {
            add+=entity.getRate();
        }
        return add / div;
    }

    public RatingDao getRatingDao() {
        return ratingDao;
    }

    public Optional<Rating> getCurrentUserRatingForASelectAnime(String currentUserLogin, Long animeid) {
        if (usersRepository.findByUsername(currentUserLogin).isPresent()) {
            return this.ratingDao.findById(new RatingId(usersRepository.findByUsername(currentUserLogin).get().getId(), animeid)).map(Rating::new);
        } else {
            return Optional.empty();
        }
    }
}
