package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;

public class Lists {
    private Long id;
    private String name;
    private String creationDate;
    private String description;

    public Lists() { }

    public Lists(Long id, String name, String creationDate, String description) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
    }

    public Lists(ListsEntity listsEntity) {
        this.id = listsEntity.getId();
        this.name = listsEntity.getName();
        this.creationDate = listsEntity.getDate();
        this.description = listsEntity.getDescription();
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
}
