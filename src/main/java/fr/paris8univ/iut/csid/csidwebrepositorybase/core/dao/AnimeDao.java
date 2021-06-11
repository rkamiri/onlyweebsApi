package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeDao extends JpaRepository<AnimeEntity, Long> {

    int countAnimeEntitiesByPegiEntityNotLike(PegiEntity pegiEntity);

    List<AnimeEntity> findTop15ByTitleContainingAndPegiEntityNotLike(String researchInternationalTitle, PegiEntity pegiEntity);

    Page<AnimeEntity> findAllByPegiEntityNotLike(Pageable page, PegiEntity pegiEntity);

    @Query(value = "Select distinct a.aired, a.airing, a.episodes,a.id, a.img_url, a.pegi_id, a.synopsis, a.title, a.title_english from anime a  " +
            "inner join has_studio s on a.id=s.id_anime inner join has_producer p on a.id=p.id_anime inner join has_genre g on a.id=g.id " +
            "where (:research is null or title like CONCAT('%',:research,'%')) and (:producer is null or id_producer = :producer) and (:studio is null or id_studio = :studio) and (:genre is null or id_genre = :genre) and pegi_id != 6",
            countQuery = "select count(*) from anime a  " +
                    "inner join has_studio s on a.id=s.id_anime inner join has_producer p on a.id=p.id_anime inner join has_genre g on a.id=g.id " +
                    "where (:research is null or title like CONCAT('%',:research,'%')) and (:producer is null or id_producer = :producer) and (:studio is null or id_studio = :studio) and (:genre is null or id_genre = :genre) and pegi_id != 6",
            nativeQuery = true)
    Page<AnimeEntity> findAllByCompleteResearch(@Param("research")String research, @Param("producer") String producer, @Param("studio") String studio, @Param("genre") String genre, Pageable page);

    @Query(value = "Select distinct a.aired, a.airing, a.episodes,a.id, a.img_url, a.pegi_id, a.synopsis, a.title, a.title_english from anime a  " +
            "inner join has_studio s on a.id=s.id_anime inner join has_producer p on a.id=p.id_anime inner join has_genre g on a.id=g.id " +
            "where (:research is null or title like CONCAT('%',:research,'%')) and (:producer is null or id_producer = :producer) and (:studio is null or id_studio = :studio) and (:genre is null or id_genre = :genre) and pegi_id != 6",
            nativeQuery = true)
    List<AnimeEntity> findAllByCompleteResearchForCount(@Param("research")String research, @Param("producer") String producer, @Param("studio") String studio, @Param("genre") String genre);
    Page<AnimeEntity> findByTitleContainingAndPegiEntityNotLike(Pageable page, String researchInternationalTitle, PegiEntity hentaiEntity);

    List<AnimeEntity> findTop15ByAiringIsContainingAndPegiEntityNotLikeOrderByAiringDesc(String date, PegiEntity pegiEntity);

}
