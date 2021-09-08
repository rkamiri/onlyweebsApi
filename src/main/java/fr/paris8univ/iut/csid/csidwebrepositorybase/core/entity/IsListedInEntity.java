package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "islistedin")
public class IsListedInEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "list_id", nullable = false)
    private Long listId;

    @Column(name = "anime_id", nullable = false)
    private Long animeId;

    public IsListedInEntity() {
    }

    public IsListedInEntity(Long listId, Long animeId) {
        this.listId = listId;
        this.animeId = animeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IsListedInEntity that = (IsListedInEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
