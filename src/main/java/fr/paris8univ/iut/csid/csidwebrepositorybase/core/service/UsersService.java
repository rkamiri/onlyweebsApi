package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.AuthoritiesRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthoritiesEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ImageDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.UsersDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UsersRepository usersRepository, AuthoritiesRepository authoritiesRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.authoritiesRepository = authoritiesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UsersDto getUser(long id) {
        return new UsersDto(this.usersRepository.getOne(id));
    }

    public Optional<UsersDto> findUserByLogin(String userLogin) throws NoUserFoundException {
        try {
            return this.usersRepository.findByUsername(userLogin).map(UsersDto::new);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoUserFoundException();
        }
    }

    public UserEntity findUserEntityByUsername(String userLogin) {
        return this.usersRepository.findByUsername(userLogin).orElseThrow();
    }

    public UsersDto updateCurrentUser(UsersDto updatedUser) throws NoUserFoundException {
        UserEntity current = this.usersRepository.findById(updatedUser.getId()).orElseThrow(NoUserFoundException::new);
        if (updatedUser.getUsername() != null || !Objects.equals(updatedUser.getUsername(), current.getUsername())) {
            AuthoritiesEntity currAuthDao = this.authoritiesRepository.findById(current.getUsername()).orElseThrow();
            this.authoritiesRepository.delete(currAuthDao);
            currAuthDao.setUsername(updatedUser.getUsername());
            current.setUsername(updatedUser.getUsername());
            this.usersRepository.save(current);
            this.authoritiesRepository.save(currAuthDao);
        }
        if (updatedUser.getEmail() != null || !Objects.equals(updatedUser.getEmail(), current.getEmail())) {
            current.setEmail(updatedUser.getEmail());
            this.usersRepository.save(current);
        }
        if (updatedUser.getFirstname() != null || !Objects.equals(updatedUser.getFirstname(), current.getFirstname())) {
            current.setFirstname(updatedUser.getFirstname());
            this.usersRepository.save(current);
        }
        if (updatedUser.getLastname() != null || !Objects.equals(updatedUser.getLastname(), current.getLastname())) {
            current.setLastname(updatedUser.getLastname());
            this.usersRepository.save(current);
        }
        if (updatedUser.getPassword() != null) {
            current.setPassword(this.bCryptPasswordEncoder.encode(updatedUser.getPassword()));
            this.usersRepository.save(current);
        }
        if (updatedUser.getBio() != null || !Objects.equals(updatedUser.getBio(), current.getBio())) {
            current.setBio(updatedUser.getBio());
            this.usersRepository.save(current);
        }
        return new UsersDto(this.usersRepository.findById(updatedUser.getId()).orElseThrow(NoUserFoundException::new));
    }

    public Boolean checkIpAddress(String remoteAddress, String userLogin) {
        return bCryptPasswordEncoder.matches(remoteAddress, this.findUserEntityByUsername(userLogin).getIp());
    }

    public void updateIp(String newIp, String currentUserLogin) {
        UserEntity userEntity = this.findUserEntityByUsername(currentUserLogin);
        userEntity.setIp(bCryptPasswordEncoder.encode(newIp));
        this.usersRepository.save(userEntity);
    }

    public void deleteUser(UserEntity userEntity) throws NoUserFoundException {
        String encoding = bCryptPasswordEncoder.encode(userEntity.getUsername());
        String newUsername = "deleted" + encoding.substring(encoding.length() - 20);
        ImageEntity newImage = userEntity.getImage();
        newImage.setContent("hello667world".getBytes(StandardCharsets.UTF_8));
        newImage.setName("deletedImageName");
        UsersDto deletedUser = new UsersDto(
                userEntity.getId(),
                newUsername,
                userEntity.getPassword() + "667ekip",
                "Supprimé",
                "Supprimé",
                "Supprimé",
                "S",
                "Supprimé",
                "Supprimé",
                new ImageDto(userEntity.getImage()));
        this.updateCurrentUser(deletedUser);
        this.usersRepository.getOne(deletedUser.getId()).setImage(newImage);
    }

    public List<UsersDto> getUsers() {
        return this.usersRepository.findAll().stream().map(UsersDto::new).collect(Collectors.toList());
    }

    public List<UsersDto> getActiveUsers() {
        return this.usersRepository.findUsersNotDeletedAndNotBanned().stream().map(UsersDto::new).collect(Collectors.toList());
    }
}
