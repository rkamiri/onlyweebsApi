package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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
    private UserEntity isOwnedBy;

    @Column(name = "is_default")
    private int isDefault;

    public ListsEntity() {
    }

    public ListsEntity(String name, String date, String description, UserEntity isOwnedBy, int isDefault) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.isOwnedBy = isOwnedBy;
        this.isDefault = isDefault;
    }
}