package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AuthoritiesEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.UsersDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.AuthoritiesRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ImageRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ListsRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegisterService {
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthoritiesRepository authoritiesRepository;
    private final ListsRepository listsRepository;
    private final ImageRepository imageRepository;

    public RegisterService(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthoritiesRepository authoritiesRepository, ListsRepository listsRepository, ImageRepository imageRepository) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authoritiesRepository = authoritiesRepository;
        this.listsRepository = listsRepository;
        this.imageRepository = imageRepository;
    }

    public void createUser(UsersDto user, String ipAddress) {
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
        this.usersRepository.save(
                new UserEntity(
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
        UserEntity userEntity = usersRepository.findByUsername(user.getUsername()).orElseGet(UserEntity::new);
        this.listsRepository.save(new ListsEntity("Watched", dtf.format(now), "All the animes you watched", userEntity, 1));
        this.listsRepository.save(new ListsEntity("Currently watching", dtf.format(now), "All the animes your are currently watching", userEntity, 1));
        this.listsRepository.save(new ListsEntity("Plan to watch", dtf.format(now), "All the animes your are planning to watch", userEntity, 1));
        this.authoritiesRepository.save(new AuthoritiesEntity(user.getUsername(), "ROLE_USER"));
    }
}
