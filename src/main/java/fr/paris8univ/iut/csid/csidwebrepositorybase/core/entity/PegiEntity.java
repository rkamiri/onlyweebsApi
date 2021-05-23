package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "pegi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PegiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "pegi")
    private String pegi;

    public PegiEntity(Long id, String pegi) {
        this.id = id;
        this.pegi = pegi;
    }

    public PegiEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return pegi;
    }

    public void setName(String name) {
        this.pegi = name;
    }
}
