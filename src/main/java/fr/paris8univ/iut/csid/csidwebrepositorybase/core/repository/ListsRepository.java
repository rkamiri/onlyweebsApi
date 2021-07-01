package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListsRepository extends JpaRepository<ListsEntity, Long> {
    List<ListsEntity> getListsEntitiesByIsOwnedBy(UserEntity isOwnedBy);
    List<ListsEntity> getListsEntitiesByIsOwnedByAndIsDefault(UserEntity isOwnedBy, int isDefault);
    List<ListsEntity> findByIsDefault(int isDefault);
    List<ListsEntity> findByName(String name);
    Optional<ListsEntity> findListById(long listId);
    ListsEntity getListById(long listId);
    ListsEntity findFirstByOrderByIdDesc();
    ListsEntity findOneByNameAndIsOwnedBy(String name, UserEntity isOwnedBy);
    void deleteListsEntitiesByIsOwnedBy(long isOwnedBy);
    int countByNameLike(String name);
    long count();
}
