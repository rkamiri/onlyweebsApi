package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthoritiesEntity;
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
    private final AuthoritiesRepository authoritiesRepository;
    private final ListsDao listsDao;
    private final ImageRepository imageRepository;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public RegisterRepository(UsersDao usersDao, BCryptPasswordEncoder bCryptPasswordEncoder, AuthoritiesRepository authoritiesRepository, ListsDao listsDao, ImageRepository imageRepository) {
        this.usersDao = usersDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authoritiesRepository = authoritiesRepository;
        this.listsDao = listsDao;
        this.imageRepository = imageRepository;
    }

    public void createUser(Users user, String ipAddress) {
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
                        bCryptPasswordEncoder.encode(ipAddress),
                        this.imageRepository.getOne(defaultImageId)
                )
        );
        UsersEntity usersEntity = usersDao.findByUsername(user.getUsername()).orElseGet(UsersEntity::new);
        this.listsDao.save(new ListsEntity("Watched", dtf.format(now), "All the animes you watched", usersEntity, 1));
        this.listsDao.save(new ListsEntity("Currently watching", dtf.format(now), "All the animes your are currently watching", usersEntity, 1));
        this.listsDao.save(new ListsEntity("Plan to watch", dtf.format(now), "All the animes your are planning to watch", usersEntity, 1));
        this.authoritiesRepository.save(new AuthoritiesEntity(user.getUsername(), "ROLE_USER"));
    }
}
