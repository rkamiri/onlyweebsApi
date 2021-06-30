package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import lombok.Data;

@Data
public class PegiDto {
    private Long id;
    private String name;

    public PegiDto(PegiEntity pegiEntity) {
        this.id = pegiEntity.getId();
        this.name = pegiEntity.getPegi();
    }
}
