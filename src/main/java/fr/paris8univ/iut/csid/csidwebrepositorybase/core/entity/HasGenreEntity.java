package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(Long idAnime) {
        this.idAnime = idAnime;
    }

    public Long getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Long idProducer) {
        this.idGenre = idProducer;
    }
}
