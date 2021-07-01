package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.GenreRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.GenreDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDto> findAll(){
        return this.genreRepository.findAll().stream().map(GenreDto::new).collect(Collectors.toList());
    }
}
