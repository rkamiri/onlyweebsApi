package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;

public class Lists {
    private Long id;
    private String name;
    private String creationDate;
    private String description;
    private Long isOwnedBy;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIsOwnedBy() {
        return isOwnedBy;
    }

    public void setIsOwnedBy(Long isOwnedBy) {
        this.isOwnedBy = isOwnedBy;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
