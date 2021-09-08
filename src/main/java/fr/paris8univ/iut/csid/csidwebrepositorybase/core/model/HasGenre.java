package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasGenreEntity;

public class HasGenre {
    private Long id;
    private Long idAnime;
    private Long idGenre;

    public HasGenre(HasGenreEntity genreEntity) {
        this.id = genreEntity.getId();
        this.idAnime = genreEntity.getIdAnime();
        this.idGenre = genreEntity.getIdGenre();
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

    public void setIdGenre(Long idGenre) {
        this.idGenre = idGenre;
    }
}
