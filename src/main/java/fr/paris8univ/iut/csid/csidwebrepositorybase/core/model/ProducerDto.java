package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ProducerEntity;
import lombok.Data;

@Data
public class ProducerDto {
    private Long id;
    private String name;

    public ProducerDto(ProducerEntity producerEntity) {
        this.id = producerEntity.getId();
        this.name = producerEntity.getName();
    }
}
