package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ImageDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UsersRepository {

    private final UsersDao usersDao;
    private final AuthoritiesRepository authoritiesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersRepository(UsersDao usersDao, AuthoritiesRepository authoritiesRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersDao = usersDao;
        this.authoritiesRepository = authoritiesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<UsersEntity> findById(long id) {
        return this.usersDao.findById(id);
    }

    public Optional<UsersEntity> findByUsername(String username) {
        return this.usersDao.findByUsername(username);
    }

    public UsersEntity findUserEntityByUsername(String username) {
        return this.usersDao.findByUsername(username).orElseThrow();
    }

    public Users updateCurrentUser(Users updatedUser) throws NoUserFoundException {
        UsersEntity current = this.usersDao.findById(updatedUser.getId()).orElseThrow(NoUserFoundException::new);

        if (updatedUser.getUsername() != null || !Objects.equals(updatedUser.getUsername(), current.getUsername())) {
            AuthoritiesEntity currAuthDao = this.authoritiesRepository.findById(current.getUsername()).orElseThrow();
            this.authoritiesRepository.delete(currAuthDao);
            currAuthDao.setUsername(updatedUser.getUsername());
            current.setUsername(updatedUser.getUsername());
            this.usersDao.save(current);
            this.authoritiesRepository.save(currAuthDao);
        }
        if (updatedUser.getEmail() != null || !Objects.equals(updatedUser.getEmail(), current.getEmail())) {
            current.setEmail(updatedUser.getEmail());
            this.usersDao.save(current);
        }
        if (updatedUser.getFirstname() != null || !Objects.equals(updatedUser.getFirstname(), current.getFirstname())) {
            current.setFirstname(updatedUser.getFirstname());
            this.usersDao.save(current);
        }
        if (updatedUser.getLastname() != null || !Objects.equals(updatedUser.getLastname(), current.getLastname())) {
            current.setLastname(updatedUser.getLastname());
            this.usersDao.save(current);
        }
        if (updatedUser.getPassword() != null) {
            current.setPassword(this.bCryptPasswordEncoder.encode(updatedUser.getPassword()));
            this.usersDao.save(current);
        }
        if (updatedUser.getBio() != null || !Objects.equals(updatedUser.getBio(), current.getBio())) {
            current.setBio(updatedUser.getBio());
            this.usersDao.save(current);
        }

        return new Users(this.usersDao.findById(updatedUser.getId()).orElseThrow(NoUserFoundException::new));
    }

    public Boolean checkIpAddress(String remoteIp, String userLogin) {
        return bCryptPasswordEncoder.matches(remoteIp, this.findUserEntityByUsername(userLogin).getIp());
    }

    public void updateIp(String newIp, String currentUserLogin) {
        UsersEntity usersEntity = this.findUserEntityByUsername(currentUserLogin);
        usersEntity.setIp(bCryptPasswordEncoder.encode(newIp));
        this.usersDao.save(usersEntity);
    }

    public void deleteUser(UsersEntity usersEntity) throws NoUserFoundException {
        String encoding = bCryptPasswordEncoder.encode(usersEntity.getUsername());
        String newUsername = "deleted" + encoding.substring(encoding.length() - 20);
        ImageEntity newImage = usersEntity.getImage();
        newImage.setContent("hello667world".getBytes(StandardCharsets.UTF_8));
        newImage.setName("deletedImageName");
        Users deletedUser = new Users(
                usersEntity.getId(),
                newUsername,
                usersEntity.getPassword() + "667ekip",
                "Supprimé",
                "Supprimé",
                "Supprimé",
                "S",
                "Supprimé",
                "Supprimé",
                new ImageDto(usersEntity.getImage()));
        this.updateCurrentUser(deletedUser);
        this.usersDao.getOne(deletedUser.getId()).setImage(newImage);
    }

    public UsersEntity getUser(long id) {
        return this.usersDao.getOne(id);
    }
}
