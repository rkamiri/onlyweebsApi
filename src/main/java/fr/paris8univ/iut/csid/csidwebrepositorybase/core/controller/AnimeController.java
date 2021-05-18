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

    @GetMapping("/pagination/{page}")
    public List<Anime> getAnimes(@PathVariable(value = "page", required = true) int page) throws URISyntaxException {
        return this.animeService.getAnimes(page);
    }

    @GetMapping("/{id}")
    public Anime getOneAnime(@PathVariable(value = "id", required = true) Long idAnime) throws NoAnimeException {
        return this.animeService.getOneAnime(idAnime);
    }

    @GetMapping("/research/{research}")
    public List<Anime> researchAnimes(@PathVariable(value = "research", required = true) String research) {
        return this.animeService.researchAnimes(research);
    }

    @GetMapping("/research/{research}/pagination/{page}")
    public List<Anime> researchAnimesPagination(@PathVariable(value = "research", required = true) String research, @PathVariable(value = "page", required = true) int page) {
        return this.animeService.researchAnimesPagination(research, page);
    }

    @GetMapping("/research/{research}/count")
    public int getResearchPageCount(@PathVariable(value = "research", required = true) String research){
        return this.animeService.getResearchCount(research);
    }

    @GetMapping("/count")
    public int getPageCount(){
        return this.animeService.getCount();
    }
}
