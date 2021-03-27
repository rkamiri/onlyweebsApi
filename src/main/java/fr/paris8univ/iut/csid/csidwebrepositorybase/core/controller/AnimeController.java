package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.AnimeService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoAnimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/animes")
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping
    public List<Anime> getAnimes() throws URISyntaxException {
        return this.animeService.getAnimes();
    }

    @GetMapping("/{id}")
    public Anime getOneAnime(@PathVariable(value = "id", required = true) Long idAnime) throws NoAnimeException {
        return this.animeService.getOneAnime(idAnime);
    }
}
