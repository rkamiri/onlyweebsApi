package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthoritiesEntity that = (AuthoritiesEntity) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
