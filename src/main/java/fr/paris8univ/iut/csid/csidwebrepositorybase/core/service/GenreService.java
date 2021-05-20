package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GenreDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ProducerDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.GenreEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ProducerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreDao genreDao;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public List<GenreEntity> findAll(){
        return this.genreDao.findAll();
    }
}
