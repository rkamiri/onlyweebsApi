package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GenreDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.StudioDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.GenreEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.StudioEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioService {

    private final StudioDao studioDao;

    public StudioService(StudioDao studioDao) {
        this.studioDao = studioDao;
    }

    public List<StudioEntity> findAll(){
        return this.studioDao.findAll();
    }
}
