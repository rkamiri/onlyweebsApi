package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AuthDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.UsersDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterRepository {

    private final UsersDao usersDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthDao authDao;

    @Autowired
    public RegisterRepository(UsersDao usersDao, BCryptPasswordEncoder bCryptPasswordEncoder, AuthDao authDao) {
        this.usersDao = usersDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authDao = authDao;
    }

    public void createUser(Users user) {
        this.usersDao.save(
                new UsersEntity(
                        user.getUsername(),
                        bCryptPasswordEncoder.encode(user.getPassword()),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getEmail(),
                        user.getGender(),
                        "No bio yet."
                )
        );
        this.authDao.save(new AuthEntity(user.getUsername(), "root"));
    }
}
