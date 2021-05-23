package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasProducerEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.HasProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HasProducerDao extends JpaRepository<HasProducerEntity, Long> {

    List<HasProducerEntity> findAllByIdAnime(Long idAnime);
}
