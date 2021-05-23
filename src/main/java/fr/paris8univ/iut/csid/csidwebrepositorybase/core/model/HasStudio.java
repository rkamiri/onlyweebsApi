package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasStudioEntity;

public class HasStudio {
    private Long id;
    private Long idAnime;
    private Long idStudio;

    public HasStudio(HasStudioEntity hasStudioEntity) {
        this.id = hasStudioEntity.getId();
        this.idAnime = hasStudioEntity.getIdAnime();
        this.idStudio = hasStudioEntity.getIdStudio();
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

    public Long getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(Long idStudio) {
        this.idStudio = idStudio;
    }
}
