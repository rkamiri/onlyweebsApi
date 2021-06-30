package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {
    List<RatingEntity> getRatingEntitiesByUserId(Long userId);
    List<RatingEntity> getRatingEntitiesByAnimeId(Long animeId);
}
