package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListsDao extends JpaRepository<ListsEntity, Long> {
    void deleteListsEntitiesByIsOwnedBy(Long isOwnedBy);
    List<ListsEntity> getListsEntitiesByIsOwnedBy(Long isOwnedBy);
    long count();
}
