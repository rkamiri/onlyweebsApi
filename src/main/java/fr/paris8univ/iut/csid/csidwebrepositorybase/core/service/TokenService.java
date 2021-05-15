package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.TokenDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.TokenEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenDao tokenDao;

    public TokenService(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    public TokenEntity createToken(UsersEntity user) {
        TokenEntity tokenEntity = new TokenEntity(user);
        this.tokenDao.save(tokenEntity);
        return tokenEntity;
    }
}
