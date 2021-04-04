package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HasImageDao extends JpaRepository<HasImageEntity, Long> {
}
