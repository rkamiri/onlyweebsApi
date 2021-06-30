package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "authorities")
public class AuthoritiesEntity {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "authority")
    private String authority;

    public AuthoritiesEntity(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public AuthoritiesEntity() {
    }
}
