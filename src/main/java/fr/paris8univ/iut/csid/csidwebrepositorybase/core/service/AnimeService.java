package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Genre;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Producer;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Studio;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final AnimeDao animeDao;
    private final HasStudioDao hasStudioDao;
    private final HasGenreDao hasGenreDao;
    private final HasProducerDao hasProducerDao;
    private final GenreDao genreDao;
    private final ProducerDao producerDao;
    private final StudioDao studioDao;

    public AnimeService(AnimeRepository animeRepository, AnimeDao animeDao, HasStudioDao hasStudioDao, HasGenreDao hasGenreDao, HasProducerDao hasProducerDao, GenreDao genreDao, ProducerDao producerDao, StudioDao studioDao) {
        this.animeRepository = animeRepository;
        this.animeDao = animeDao;
        this.hasStudioDao = hasStudioDao;
        this.hasGenreDao = hasGenreDao;
        this.hasProducerDao = hasProducerDao;
        this.genreDao = genreDao;
        this.producerDao = producerDao;
        this.studioDao = studioDao;
    }

    public List<Anime> getAnimes(int page) {
        return this.animeRepository.findAllAnime(page).stream().map(Anime::new).collect(Collectors.toList());
    }

    public int getCount() {
        return this.animeRepository.getCount();
    }

    public Anime getOneAnime(Long id) {
        return this.animeRepository.findOneAnime(id).map(Anime::new).orElseThrow();
    }

    public List<Anime> researchAnimes(String research) {
        return this.animeRepository.researchAnimes(research);
    }

    /*public List<Anime> researchAnimesPagination(String research, int page) {
        return this.animeRepository.researchAnimesPagination(research, page);
    }*/

    public int getResearchCount(String research, String producer, String studio, String genre) {
        return this.animeRepository.getResearchCount(research, producer, studio, genre);
    }

    public List<Anime> researchAnimesPagination(String research, String producer, String studio, String genre, int page) {
        return this.animeRepository.researchAnimesPagination(research, producer, studio, genre, page);
    }

    public List<Studio> getAnimeStudios(Long idAnime) {
        List<HasStudioEntity> hasStudioEntity = hasStudioDao.findAllByIdAnime(idAnime);
        List<StudioEntity> studioEntities = new ArrayList<>();
        for (HasStudioEntity entity : hasStudioEntity) {
            studioEntities.add(studioDao.findById(entity.getIdStudio()).orElseThrow());
        }
        return studioEntities.stream().map(Studio::new).collect(Collectors.toList());
    }

    public List<Producer> getAnimeProducers(Long idAnime) {
        List<HasProducerEntity> hasProducerEntity = hasProducerDao.findAllByIdAnime(idAnime);
        List<ProducerEntity> producerEntities = new ArrayList<>();
        for (HasProducerEntity entity : hasProducerEntity) {
            producerEntities.add(producerDao.findById(entity.getIdProducer()).orElseThrow());
        }
        return producerEntities.stream().map(Producer::new).collect(Collectors.toList());
    }

    public List<Genre> getAnimeGenres(Long idAnime) {
        List<HasGenreEntity> hasGenreEntities = hasGenreDao.findAllByIdAnime(idAnime);
        List<GenreEntity> genreEntities = new ArrayList<>();
        for (HasGenreEntity entity : hasGenreEntities) {
            genreEntities.add(genreDao.findById(entity.getIdGenre()).orElseThrow());
        }
        return genreEntities.stream().map(Genre::new).collect(Collectors.toList());
    }

    public List<Anime> getAllAnimes() {
        List<Anime> animeList = new ArrayList<>();
        for (AnimeEntity animeEntity : this.animeDao.findAll()) {
            animeList.add(new Anime(animeEntity));
        }
        return animeList;
    }

    public List<Anime> getLatestAnimes() {
        return this.animeRepository.getLatestAnimes();
    }
}
