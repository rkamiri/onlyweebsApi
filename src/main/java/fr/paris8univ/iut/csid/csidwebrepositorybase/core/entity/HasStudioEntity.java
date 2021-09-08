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
@Table(name = "has_studio")
public class HasStudioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "id_anime")
    private Long idAnime;

    @Column(name = "id_studio")
    private Long idStudio;

    public HasStudioEntity(Long id, Long idAnime, Long idStudio) {
        this.id = id;
        this.idAnime = idAnime;
        this.idStudio = idStudio;
    }

    public HasStudioEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HasStudioEntity that = (HasStudioEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
