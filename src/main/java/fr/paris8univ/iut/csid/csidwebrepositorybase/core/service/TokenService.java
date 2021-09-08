package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.TokenEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.UpdatePasswordDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.TokenRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenService(TokenRepository tokenRepository, UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.tokenRepository = tokenRepository;
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public TokenEntity createToken(UserEntity user) {
        TokenEntity tokenEntity = new TokenEntity(user);
        this.tokenRepository.save(tokenEntity);
        return tokenEntity;
    }

    public boolean putPassword(UpdatePasswordDto newPassword) {
        TokenEntity tokenEntity;
        try {
            tokenEntity = this.tokenRepository.findTokenEntityByToken(newPassword.getToken());
            if (tokenEntity.getExpirationDate().after(new Date())) {
                UserEntity userEntity = tokenEntity.getUsersEntity();
                userEntity.setPassword(this.bCryptPasswordEncoder.encode(newPassword.getNewPassword()));
                this.usersRepository.save(userEntity);
                this.tokenRepository.delete(tokenEntity);
            }
        } catch (Exception ignored) {
            return false;
        }
        return true;
    }
}
