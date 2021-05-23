package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HasGenreDao extends JpaRepository<HasGenreEntity, Long> {

    List<HasGenreEntity> findAllByIdAnime(Long idAnime);
}
