package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.RatingDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Rating;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingRepository {

    private final RatingDao ratingDao;
    public final UsersRepository ur;

    @Autowired
    public RatingRepository(RatingDao ratingDao, UsersRepository ur) {
        this.ratingDao = ratingDao;
        this.ur = ur;
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
}
