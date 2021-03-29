package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoAnimeException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAnimes() throws URISyntaxException {
        return this.animeRepository.findAllAnime();
    }

    public Anime getOneAnime(Long id) throws NoAnimeException {
        return this.animeRepository.findOneAnime(id).orElseThrow(NoAnimeException::new);
    }

    public List<Anime> researchAnimes(String research) {
        return this.animeRepository.researchAnimes(research);
    }
}
