package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
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
}
