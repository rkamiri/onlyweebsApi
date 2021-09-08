package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimeRepository extends JpaRepository<AnimeEntity, Long> {

    int countAnimeEntitiesByPegiEntityNotLike(PegiEntity pegiEntity);

    List<AnimeEntity> findTop15ByTitleContainingAndPegiEntityNotLike(String researchInternationalTitle, PegiEntity pegiEntity);

    Page<AnimeEntity> findAllByPegiEntityNotLike(Pageable page, PegiEntity pegiEntity);

    @Query(value = "Select distinct a.aired, a.airing, a.episodes,a.id, a.img_url, a.pegi_id, a.synopsis, a.title, a.title_english from anime a  " +
            "inner join has_studio s on a.id=s.id_anime inner join has_producer p on a.id=p.id_anime inner join has_genre g on a.id=g.id " +
            "where (:producer is null or id_producer = :producer) and (:studio is null or id_studio = :studio) and (:genre is null or id_genre = :genre) and pegi_id != 6",
            countQuery = "select count(*) from anime a  " +
                    "inner join has_studio s on a.id=s.id_anime inner join has_producer p on a.id=p.id_anime inner join has_genre g on a.id=g.id " +
                    "where (:producer is null or id_producer = :producer) and (:studio is null or id_studio = :studio) and (:genre is null or id_genre = :genre) and pegi_id != 6",
            nativeQuery = true)
    Page<AnimeEntity> findAllByCompleteResearch(@Param("producer") Long producer, @Param("studio") Long studio, @Param("genre") Long genre, Pageable page);

    @Query(value = "Select distinct a.aired, a.airing, a.episodes,a.id, a.img_url, a.pegi_id, a.synopsis, a.title, a.title_english from anime a  " +
            "inner join has_studio s on a.id=s.id_anime inner join has_producer p on a.id=p.id_anime inner join has_genre g on a.id=g.id " +
            "where (:producer is null or id_producer = :producer) and (:studio is null or id_studio = :studio) and (:genre is null or id_genre = :genre) and pegi_id != 6",
            nativeQuery = true)
    List<AnimeEntity> findAllByCompleteResearchForCount(@Param("producer") Long producer, @Param("studio") Long studio, @Param("genre") Long genre);

    Page<AnimeEntity> findByTitleContainingAndPegiEntityNotLike(Pageable page, String researchInternationalTitle, PegiEntity hentaiEntity);

    List<AnimeEntity> findTop15ByAiringIsContainingAndPegiEntityNotLikeOrderByAiringDesc(String date, PegiEntity pegiEntity);

    long count();

    List<AnimeEntity> findByTitleContainingAndPegiEntityNotLike(String research, PegiEntity hentaiEntity);

    Optional<AnimeEntity> findOneById(Long animeId);
}
