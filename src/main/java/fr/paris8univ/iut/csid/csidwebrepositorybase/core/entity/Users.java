package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

public class Users {
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String bio;

    public Users() {}

    public Users(UsersEntity usersEntity) {
        this.id=usersEntity.getId();
        this.username = usersEntity.getUsername();
        this.password = usersEntity.getPassword();
        this.firstname = usersEntity.getFirstname();
        this.lastname = usersEntity.getLastname();
        this.email = usersEntity.getEmail();
        this.gender = usersEntity.getGender();
        this.bio = usersEntity.getBio();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getBio() {
        return bio;
    }
}
