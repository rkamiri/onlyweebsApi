package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AnimeDao extends JpaRepository<AnimeEntity, Long> {

   List<AnimeEntity> findTop15ByTitleContaining(String researchInternationalTitle);
   Page<AnimeEntity> findByTitleContaining(Pageable page, String researchInternationalTitle);
   List<AnimeEntity> findByTitleContaining(String researchInternationalTitle);
   //Page<AnimeEntity> findByGenreNotContaining (Pageable page, String hentai);
   //List<AnimeEntity> findByGenreNotContaining(String hentai);
}
