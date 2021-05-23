package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasStudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HasStudioDao extends JpaRepository<HasStudioEntity, Long> {

    List<HasStudioEntity> findAllByIdAnime(Long idAnime);
}
