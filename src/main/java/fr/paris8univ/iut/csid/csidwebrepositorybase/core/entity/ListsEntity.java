package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "is_owned_by")
    private UsersEntity isOwnedBy;

    @Column(name = "is_default")
    private int is_default;

    public ListsEntity() {}

    public ListsEntity(String name, String date, String description, UsersEntity isOwnedBy, int is_default) {
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

    public UsersEntity getIsOwnedBy() {
        return isOwnedBy;
    }
}