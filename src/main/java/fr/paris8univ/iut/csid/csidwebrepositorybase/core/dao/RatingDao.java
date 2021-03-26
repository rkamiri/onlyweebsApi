package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingDao extends JpaRepository<RatingEntity, RatingId> {
}
