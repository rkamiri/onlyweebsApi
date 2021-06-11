package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;

public class Lists {
    private Long id;
    private String name;
    private String creationDate;
    private String description;
    private UsersEntity isOwnedBy;
    private int isDefault;

    public Lists() { }

    public Lists(ListsEntity listsEntity) {
        this.id = listsEntity.getId();
        this.name = listsEntity.getName();
        this.creationDate = listsEntity.getDate();
        this.description = listsEntity.getDescription();
        this.isOwnedBy = listsEntity.getIsOwnedBy();
        this.isDefault = listsEntity.getIs_default();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public UsersEntity getIsOwnedBy() {
        return isOwnedBy;
    }

    public int getIsDefault() {
        return isDefault;
    }
}
