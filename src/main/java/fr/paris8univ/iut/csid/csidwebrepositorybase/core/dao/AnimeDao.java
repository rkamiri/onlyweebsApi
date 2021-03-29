package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeDao extends JpaRepository<AnimeEntity, Long> {

   List<AnimeEntity> findByInternationalTitleStartingWithOrTitleStartingWith(String researchInternationalTitle, String researchTitle);
}
