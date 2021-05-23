package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeDao extends JpaRepository<AnimeEntity, Long> {

    List<AnimeEntity> findAllByPegiEntityNotLike(PegiEntity pegiEntity);

    List<AnimeEntity> findTop15ByTitleContainingAndPegiEntityNotLike(String researchInternationalTitle, PegiEntity pegiEntity);

    List<AnimeEntity> findByTitleContainingAndPegiEntityNotLike(String researchInternationalTitle, PegiEntity pegiEntity);

    Page<AnimeEntity> findAllByPegiEntityNotLike(Pageable page, PegiEntity pegiEntity);

    Page<AnimeEntity> findByTitleContainingAndPegiEntityNotLike(Pageable page, String researchInternationalTitle, PegiEntity hentaiEntity);
}
