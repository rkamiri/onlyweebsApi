package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.GenreEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ProducerEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.StudioEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.AnimeService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoAnimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public List<Anime> getAnimes(@PathVariable(value = "page") int page) {
        return this.animeService.getAnimes(page);
    }

    @GetMapping("/all")
    public List<Anime> getAllAnimes() {
        return this.animeService.getAllAnimes();
    }

    @GetMapping("/{id}")
    public Anime getOneAnime(@PathVariable(value = "id") Long idAnime) throws NoAnimeException {
        return this.animeService.getOneAnime(idAnime);
    }

    @GetMapping("/research/{research}")
    public List<Anime> researchAnimes(@PathVariable(value = "research") String research) {
        return this.animeService.researchAnimes(research);
    }

    @GetMapping("/research/{research}/pagination/{page}")
    public List<Anime> researchAnimesPagination(@PathVariable(value = "research") String research, @PathVariable(value = "page") int page) {
        return this.animeService.researchAnimesPagination(research, page);
    }

    @GetMapping("/research/{research}/count")
    public int getResearchPageCount(@PathVariable(value = "research") String research){
        return this.animeService.getResearchCount(research);
    }

    @GetMapping("/count")
    public int getPageCount(){
        return this.animeService.getCount();
    }

    @GetMapping("{id}/studios")
    public List<StudioEntity> getAnimeStudios(@PathVariable(value = "id") Long idAnime){
        return this.animeService.getAnimeStudios(idAnime);
    }

    @GetMapping("{id}/producers")
    public List<ProducerEntity> getAnimeProducers(@PathVariable(value = "id") Long idAnime){
        return this.animeService.getAnimeProducers(idAnime);
    }

    @GetMapping("{id}/genres")
    public List<GenreEntity> getAnimeGenres(@PathVariable(value = "id") Long idAnime){
        return this.animeService.getAnimeGenres(idAnime);
    }
}
