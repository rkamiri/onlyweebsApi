package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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
}
