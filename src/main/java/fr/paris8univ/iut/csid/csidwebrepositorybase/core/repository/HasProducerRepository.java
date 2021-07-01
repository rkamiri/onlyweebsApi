package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasProducerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HasProducerRepository extends JpaRepository<HasProducerEntity, Long> {

    List<HasProducerEntity> findAllByIdAnime(Long idAnime);
}
