package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "bio")
    private String bio;

    @Column(name = "ip")
    private String ip;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ImageEntity image;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String firstname, String lastname, String email, String gender, String bio, String ip, ImageEntity ie) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.bio = bio;
        this.ip = ip;
        this.image = ie;
    }
}
