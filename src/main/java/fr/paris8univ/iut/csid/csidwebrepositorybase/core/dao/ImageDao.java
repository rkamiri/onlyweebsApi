package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDao extends JpaRepository<ImageEntity, Long> {
}
