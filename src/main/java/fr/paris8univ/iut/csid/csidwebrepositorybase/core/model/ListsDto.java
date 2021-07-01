package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import lombok.Data;

@Data
public class ListsDto {
    private Long id;
    private String name;
    private String creationDate;
    private String description;
    private UserEntity isOwnedBy;
    private int isDefault;

    public ListsDto() {
    }

    public ListsDto(ListsEntity listsEntity) {
        this.id = listsEntity.getId();
        this.name = listsEntity.getName();
        this.creationDate = listsEntity.getDate();
        this.description = listsEntity.getDescription();
        this.isOwnedBy = listsEntity.getIsOwnedBy();
        this.isDefault = listsEntity.getIsDefault();
    }
}
