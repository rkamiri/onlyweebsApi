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
    private Long is_owned_by;

    @Column(name = "is_default")
    private int is_default;

    public ListsEntity() {}

    public ListsEntity(String name, String date, String description, Long is_owned_by, int is_default) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.is_owned_by = is_owned_by;
        this.is_default = is_default;
    }

    public ListsEntity(Lists lists) {
        this.name = lists.getName();
        this.date = lists.getCreationDate();
        this.description = lists.getDescription();
        this.is_owned_by = lists.getIsOwnedBy();
        this.is_default = lists.getIsDefault();
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIs_owned_by() {
        return is_owned_by;
    }

    public void setIs_owned_by(Long is_owned_by) {
        this.is_owned_by = is_owned_by;
    }
}
