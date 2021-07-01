package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegiRepository extends JpaRepository<PegiEntity, Long> {
    PegiEntity findOneById(Long hentaiPegiId);
}
