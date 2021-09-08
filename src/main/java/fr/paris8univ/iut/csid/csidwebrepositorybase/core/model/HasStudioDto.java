package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasStudioEntity;
import lombok.Data;

@Data
public class HasStudioDto {
    private Long id;
    private Long idAnime;
    private Long idStudio;

    public HasStudioDto(HasStudioEntity hasStudioEntity) {
        this.id = hasStudioEntity.getId();
        this.idAnime = hasStudioEntity.getIdAnime();
        this.idStudio = hasStudioEntity.getIdStudio();
    }
}
