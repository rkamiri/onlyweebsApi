package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/animes")
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @GetMapping(value = "/pagination/{page}")
    public ResponseEntity<List<AnimeDto>> getAnimes(@PathVariable(value = "page") int page) {
        CacheControl cacheControl = CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate();
        List<AnimeDto> content = this.animeService.getAllAnimes(page);
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnimeDto>> getAllAnimes() {
        CacheControl cacheControl = CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate();
        List<AnimeDto> content = this.animeService.getAllAnimes();
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeDto> getOneAnime(@PathVariable(value = "id") Long idAnime) {
        CacheControl cacheControl = CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate();
        AnimeDto content = this.animeService.getAnime(idAnime);
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }

    @GetMapping("/research/{research}")
    public List<AnimeDto> researchAnimes(@PathVariable(value = "research") String research) {
        return this.animeService.researchAnimes(research);
    }

    @GetMapping("/count")
    public int getPageCount() {
        return this.animeService.getNumberOfAnimes();
    }

    @GetMapping("{id}/studios")
    public List<StudioDto> getAnimeStudios(@PathVariable(value = "id") Long idAnime) {
        return this.animeService.getAnimeStudios(idAnime);
    }

    @GetMapping("{id}/producers")
    public List<ProducerDto> getAnimeProducers(@PathVariable(value = "id") Long idAnime) {
        return this.animeService.getAnimeProducers(idAnime);
    }

    @GetMapping("{id}/genres")
    public List<GenreDto> getAnimeGenres(@PathVariable(value = "id") Long idAnime) {
        return this.animeService.getAnimeGenres(idAnime);
    }

    @GetMapping("/latest")
    public List<AnimeDto> getLatestAnimes() {
        return this.animeService.getLatestAnimes();
    }

    @GetMapping("/research/{research}/pagination/{page}")
    public List<AnimeDto> researchAnimesPagination(@PathVariable(value = "research") String research, @PathVariable(value = "page") int page) {
        return this.animeService.researchAnimesPagination(research, page);
    }

    @GetMapping("/research/{research}/count")
    public int getResearchPageCount(@PathVariable(value = "research") String research) {
        return this.animeService.getResearchCount(research);
    }

    @PostMapping("/research/pagination/{page}")
    public List<AnimeDto> researchAdvancedAnimesPagination(@RequestBody AnimeResearchDto animeResearchDto, @PathVariable(value = "page") int page) {
        return this.animeService.researchAnimesPagination(animeResearchDto.getProducer(), animeResearchDto.getStudio(), animeResearchDto.getGenre(), page);
    }

    @PostMapping("/research/count")
    public int getAdvancedResearchPageCount(@RequestBody AnimeResearchDto animeResearchDto) {
        return this.animeService.getResearchCount(animeResearchDto.getProducer(), animeResearchDto.getStudio(), animeResearchDto.getGenre());
    }

}
