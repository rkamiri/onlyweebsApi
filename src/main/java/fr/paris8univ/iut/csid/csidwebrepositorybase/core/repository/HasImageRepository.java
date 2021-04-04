package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.HasImageDao;
import org.springframework.stereotype.Repository;

@Repository
public class HasImageRepository {

    private final HasImageDao hasImageDao;

    public HasImageRepository(HasImageDao hasImageDao) {
        this.hasImageDao = hasImageDao;
    }

    public Long getImageId(Long id) {
        return this.hasImageDao.getOne(id).getImageid();
    }
}
