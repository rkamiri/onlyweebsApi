package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.TokenEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findTokenEntityByUserEntity(UserEntity userEntity);
    TokenEntity findTokenEntityByToken(String token);
}
