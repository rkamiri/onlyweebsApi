package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.IsListedInEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsListedInDao extends JpaRepository<IsListedInEntity, Long> {
    void deleteIsListedInEntitiesByListId(long listId);
}
