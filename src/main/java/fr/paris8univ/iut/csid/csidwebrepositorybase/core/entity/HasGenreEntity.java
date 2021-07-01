package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "has_genre")
public class HasGenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "id_anime")
    private Long idAnime;

    @Column(name = "id_genre")
    private Long idGenre;

    public HasGenreEntity(Long id, Long idAnime, Long idGenre) {
        this.id = id;
        this.idAnime = idAnime;
        this.idGenre = idGenre;
    }

    public HasGenreEntity() {
    }
}
