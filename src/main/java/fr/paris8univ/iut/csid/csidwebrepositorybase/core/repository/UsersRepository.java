package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    long count();

    @Query(value = "SELECT * FROM users WHERE users.username NOT LIKE 'deleted%' AND users.username NOT LIKE 'banned%'",
            nativeQuery = true)
    List<UserEntity> findUsersNotDeletedAndNotBanned();
}
