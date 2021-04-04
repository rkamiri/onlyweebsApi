package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AuthDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.HasImageDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.UsersDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterRepository {

    private final UsersDao usersDao;
    private final HasImageDao hasImageDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthDao authDao;

    @Autowired
    public RegisterRepository(UsersDao usersDao, HasImageDao hasImageDao, BCryptPasswordEncoder bCryptPasswordEncoder, AuthDao authDao) {
        this.usersDao = usersDao;
        this.hasImageDao = hasImageDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authDao = authDao;
    }

    public void createUser(Users user) {
        long defaultImageId;
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
        switch (user.getGender()) {
            case "M": defaultImageId = 1L;
                break;
            case "F": defaultImageId = 2L;
                break;
            default: defaultImageId = 3L;
                break;
        }
        this.hasImageDao.save(new HasImageEntity(usersDao.findByUsername(user.getUsername()).get().getId(), defaultImageId));
        this.authDao.save(new AuthEntity(user.getUsername(), "root"));
    }
}
