package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.StudioEntity;
import lombok.Data;

@Data
public class StudioDto {
    private Long id;
    private String name;

    public StudioDto(StudioEntity studioEntity) {
        this.id = studioEntity.getId();
        this.name = studioEntity.getName();
    }
}
