package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "has_producer")
public class HasProducerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "id_anime")
    private Long idAnime;

    @Column(name = "id_producer")
    private Long idProducer;

    public HasProducerEntity(Long id, Long idAnime, Long idProducer) {
        this.id = id;
        this.idAnime = idAnime;
        this.idProducer = idProducer;
    }

    public HasProducerEntity() {
    }
}
