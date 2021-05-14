package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AuthDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.UsersDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;


@Repository
public class UsersRepository {

    private final UsersDao usersDao;
    private final AuthDao authDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersRepository(UsersDao usersDao, AuthDao authDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersDao = usersDao;
        this.authDao = authDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<Users> findByUsername(String username) {
        return this.usersDao.findByUsername(username).map(Users::new);
    }

    public UsersEntity findUserEntityByUsername(String username) {
        return this.usersDao.findByUsername(username).orElseThrow();
    }

    public Users updateCurrentUser(Users updatedUser) throws NoUserFoundException {
        UsersEntity current = this.usersDao.findById(updatedUser.getId()).orElseThrow(NoUserFoundException::new);

        if(updatedUser.getUsername()!=null || !Objects.equals(updatedUser.getUsername(), current.getUsername())){
            AuthEntity currAuthDao = this.authDao.findById(current.getUsername()).orElseThrow();
            this.authDao.delete(currAuthDao);
            currAuthDao.setUsername(updatedUser.getUsername());
            current.setUsername(updatedUser.getUsername());
            this.usersDao.save(current);
            this.authDao.save(currAuthDao);
        }
        if(updatedUser.getEmail()!=null || !Objects.equals(updatedUser.getEmail(), current.getEmail())) {
            current.setEmail(updatedUser.getEmail());
            this.usersDao.save(current);
        }
        if(updatedUser.getFirstname()!=null || !Objects.equals(updatedUser.getFirstname(), current.getFirstname())) {
            current.setFirstname(updatedUser.getFirstname());
            this.usersDao.save(current);
        }
        if(updatedUser.getLastname()!=null || !Objects.equals(updatedUser.getLastname(), current.getLastname())) {
            current.setLastname(updatedUser.getLastname());
            this.usersDao.save(current);
        }
        if(updatedUser.getPassword()!=null) {
            current.setPassword(this.bCryptPasswordEncoder.encode(updatedUser.getPassword()));
            this.usersDao.save(current);
        }
        if(updatedUser.getBio()!=null || !Objects.equals(updatedUser.getBio(), current.getBio())){
            current.setBio(updatedUser.getBio());
            this.usersDao.save(current);
        }

        return new Users(this.usersDao.findById(updatedUser.getId()).orElseThrow(NoUserFoundException::new));
    }

    public Boolean checkIpAddress(String remoteIp, String userLogin) {
        return remoteIp.equals(this.findUserEntityByUsername(userLogin).getIp());
    }

    public void updateIp(String newIp, String currentUserLogin) {
        UsersEntity usersEntity = this.findUserEntityByUsername(currentUserLogin);
        usersEntity.setIp(newIp);
        this.usersDao.save(usersEntity);
    }
}
