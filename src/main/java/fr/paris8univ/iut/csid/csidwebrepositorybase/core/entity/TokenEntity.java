package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "token")
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenid", nullable = false, unique = true)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiration_date")
    private String expirationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;

    public TokenEntity(UsersEntity usersEntity) {
        Date datePlus30Minutes = new Date(Calendar.getInstance().getTimeInMillis() + (30 * 60 * 1000));
        this.token = generateRandomAlphanumericString();
        this.expirationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datePlus30Minutes);
        this.usersEntity = usersEntity;
    }

    public TokenEntity() {

    }

    public String generateRandomAlphanumericString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 64;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }
}
