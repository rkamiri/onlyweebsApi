package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.IsListedInEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IsListedInRepository extends JpaRepository<IsListedInEntity, Long> {
    void deleteIsListedInEntitiesByListId(long listId);
    Optional<IsListedInEntity> findTopByAnimeId(long animeId);
    List<IsListedInEntity> getByListId(Long listId);
    long countByAnimeId(long animeId);
}
