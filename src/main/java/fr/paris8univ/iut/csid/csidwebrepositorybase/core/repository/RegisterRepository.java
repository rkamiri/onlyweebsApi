package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class RegisterRepository {

    private final UsersDao usersDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthDao authDao;
    private final ListsDao listsDao;
    private final ImageDao imageDao;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public RegisterRepository(UsersDao usersDao, BCryptPasswordEncoder bCryptPasswordEncoder, AuthDao authDao, ListsDao listsDao, ImageDao imageDao) {
        this.usersDao = usersDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authDao = authDao;
        this.listsDao = listsDao;
        this.imageDao = imageDao;
    }

    public void createUser(Users user) {
        long defaultImageId;
        switch (user.getGender()) {
            case "M":
                defaultImageId = 1L;
                break;
            case "F":
                defaultImageId = 2L;
                break;
            default:
                defaultImageId = 3L;
                break;
        }
        LocalDateTime now = LocalDateTime.now();
        this.usersDao.save(
                new UsersEntity(
                        user.getUsername(),
                        bCryptPasswordEncoder.encode(user.getPassword()),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getEmail(),
                        user.getGender(),
                        "No bio yet.",
                        this.imageDao.getOne(defaultImageId)
                )
        );
        long userid = usersDao.findByUsername(user.getUsername()).orElseGet(UsersEntity::new).getId();
        this.listsDao.save(new ListsEntity("Watched", dtf.format(now), "All the animes you watched", userid, 1));
        this.listsDao.save(new ListsEntity("Currently watching", dtf.format(now), "All the animes your are currently watching", userid, 1));
        this.listsDao.save(new ListsEntity("Plan to watch", dtf.format(now), "All the animes your are planning to watch", userid, 1));
        this.authDao.save(new AuthEntity(user.getUsername(), "root"));
    }
}
