package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import lombok.Data;

@Data
public class UsersDto {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String bio;
    private String ip;
    private ImageDto imageDto;

    public UsersDto() {
    }

    public UsersDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.firstname = userEntity.getFirstname();
        this.lastname = userEntity.getLastname();
        this.email = userEntity.getEmail();
        this.gender = userEntity.getGender();
        this.bio = userEntity.getBio();
        this.ip = userEntity.getIp();
        this.imageDto = new ImageDto(userEntity.getImage());
    }

    public UsersDto(Long id, String username, String password, String firstname, String lastname, String email, String gender, String bio, String ip, ImageDto imageDto) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.bio = bio;
        this.ip = ip;
        this.imageDto = imageDto;
    }
}
