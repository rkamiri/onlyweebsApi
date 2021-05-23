package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasProducerEntity;

public class HasProducer {
    private Long id;
    private Long idAnime;
    private Long idProducer;

    public HasProducer(HasProducerEntity producerEntity) {
        this.id = producerEntity.getId();
        this.idAnime = producerEntity.getIdAnime();
        this.idProducer = producerEntity.getIdProducer();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(Long idAnime) {
        this.idAnime = idAnime;
    }

    public Long getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(Long idProducer) {
        this.idProducer = idProducer;
    }
}
