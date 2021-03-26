package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public List<Anime> getAnimes() throws URISyntaxException {
        return this.animeRepository.findAllAnime();
    }

    public Anime getOneAnime(Long id) throws PasAnimeException {
        return this.animeRepository.findOneAnime(id).orElseThrow(PasAnimeException::new);
    }

    public List<Anime> researchAnimes(String research) {
        return this.animeRepository.researchAnimes(research);
    }
}
