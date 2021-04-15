package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AuthDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.HasImageDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ListsDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.UsersDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class RegisterRepository {

    private final UsersDao usersDao;
    private final HasImageDao hasImageDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthDao authDao;
    private final ListsDao listsDao;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public RegisterRepository(UsersDao usersDao, HasImageDao hasImageDao, BCryptPasswordEncoder bCryptPasswordEncoder, AuthDao authDao, ListsDao listsDao) {
        this.usersDao = usersDao;
        this.hasImageDao = hasImageDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authDao = authDao;
        this.listsDao = listsDao;
    }

    public void createUser(Users user) {
        long defaultImageId;
        LocalDateTime now = LocalDateTime.now();
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
        long userid = usersDao.findByUsername(user.getUsername()).orElseGet(UsersEntity::new).getId();
        this.listsDao.save(new ListsEntity("Watched", dtf.format(now), "All the animes you watched", userid, 1));
        this.listsDao.save(new ListsEntity("Currently watching", dtf.format(now), "All the animes your are currently watching", userid, 1));
        this.listsDao.save(new ListsEntity("Plan to watch", dtf.format(now), "All the animes your are planning to watch", userid, 1));
        switch (user.getGender()) {
            case "M": defaultImageId = 1L;
                break;
            case "F": defaultImageId = 2L;
                break;
            default: defaultImageId = 3L;
                break;
        }
        this.hasImageDao.save(new HasImageEntity(userid, defaultImageId));
        this.authDao.save(new AuthEntity(user.getUsername(), "root"));
    }
}
