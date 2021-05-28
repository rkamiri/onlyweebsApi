package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.TokenDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.UsersDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.TokenEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.UpdatePassword;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final TokenDao tokenDao;
    private final UsersDao usersDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenService(TokenDao tokenDao, UsersDao usersDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tokenDao = tokenDao;
        this.usersDao = usersDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public TokenEntity createToken(UsersEntity user) {
        TokenEntity tokenEntity = new TokenEntity(user);
        this.tokenDao.save(tokenEntity);
        return tokenEntity;
    }
  
    public boolean putPassword(UpdatePassword newPassword) {
        TokenEntity tokenEntity;
        try {
            tokenEntity = this.tokenDao.findTokenEntityByToken(newPassword.getToken());
            if (tokenEntity.getExpirationDate().after(new Date())) {
                UsersEntity usersEntity = tokenEntity.getUsersEntity();
                usersEntity.setPassword(this.bCryptPasswordEncoder.encode(newPassword.getNewPassword()));
                this.usersDao.save(usersEntity);
                this.tokenDao.delete(tokenEntity);
            }
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }
}
