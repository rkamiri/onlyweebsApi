package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;

public class Pegi {
    private Long id;
    private String name;

    public Pegi(PegiEntity pegiEntity) {
        this.id = pegiEntity.getId();
        this.name = pegiEntity.getName();
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
}
