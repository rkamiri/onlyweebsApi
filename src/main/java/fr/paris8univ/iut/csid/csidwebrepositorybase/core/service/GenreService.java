package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.GenreDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreDao genreDao;

    public GenreService(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public List<Genre> findAll(){
        return this.genreDao.findAll().stream().map(Genre::new).collect(Collectors.toList());
    }
}
