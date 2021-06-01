package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Lists;

import javax.persistence.*;

@Entity
@Table(name = "list")
public class ListsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String date;

    @Column(name = "description")
    private String description;

    @Column(name = "is_owned_by")
    private Long isOwnedBy;

    @Column(name = "is_default")
    private int is_default;

    public ListsEntity() {}

    public ListsEntity(String name, String date, String description, Long isOwnedBy, int is_default) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.isOwnedBy = isOwnedBy;
        this.is_default = is_default;
    }

    public int getIs_default() {
        return is_default;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIsOwnedBy() {
        return isOwnedBy;
    }
}
