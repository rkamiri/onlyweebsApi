package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "studio")
public class StudioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    public StudioEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudioEntity() {

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
