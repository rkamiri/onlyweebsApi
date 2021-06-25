package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.IsListedInEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IsListedInDao extends JpaRepository<IsListedInEntity, Long> {
    void deleteIsListedInEntitiesByListId(long listId);
    Optional<IsListedInEntity> findTopByAnimeId(long animeId);
    long countByAnimeId(long animeId);
}
