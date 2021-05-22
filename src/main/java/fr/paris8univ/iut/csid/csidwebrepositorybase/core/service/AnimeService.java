package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoAnimeException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.AnimeRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final HasStudioDao hasStudioDao;
    private final HasGenreDao hasGenreDao;
    private final HasProducerDao hasProducerDao;
    private final GenreDao genreDao;
    private final ProducerDao producerDao;
    private final StudioDao studioDao;

    public AnimeService(AnimeRepository animeRepository, HasStudioDao hasStudioDao, HasGenreDao hasGenreDao, HasProducerDao hasProducerDao, GenreDao genreDao, ProducerDao producerDao, StudioDao studioDao) {
        this.animeRepository = animeRepository;
        this.hasStudioDao = hasStudioDao;
        this.hasGenreDao = hasGenreDao;
        this.hasProducerDao = hasProducerDao;
        this.genreDao = genreDao;
        this.producerDao = producerDao;
        this.studioDao = studioDao;
    }

    public List<Anime> getAnimes(int page) {
        return this.animeRepository.findAllAnime(page);
    }

    public int getCount() {
        return this.animeRepository.getCount();
    }

    public Anime getOneAnime(Long id) throws NoAnimeException {
        return this.animeRepository.findOneAnime(id).orElseThrow(NoAnimeException::new);
    }

    public List<Anime> researchAnimes(String research) {
        return this.animeRepository.researchAnimes(research);
    }

    public List<Anime> researchAnimesPagination(String research, int page) {
        return this.animeRepository.researchAnimesPagination(research, page);
    }

    public int getResearchCount(String research) {
        return this.animeRepository.getResearchCount(research);
    }

    public List<StudioEntity> getAnimeStudios(Long idAnime) {
        List<HasStudioEntity> hasStudioEntity = hasStudioDao.findAllByIdAnime(idAnime);
        List<StudioEntity> studioEntities = new ArrayList<>();
        for (HasStudioEntity entity : hasStudioEntity) {
            studioEntities.add(studioDao.findById(entity.getIdStudio()).orElseThrow());
        }
        return studioEntities;
    }

    public List<ProducerEntity> getAnimeProducers(Long idAnime) {
        List<HasProducerEntity> hasProducerEntity = hasProducerDao.findAllByIdAnime(idAnime);
        List<ProducerEntity> producerEntities = new ArrayList<>();
        for (HasProducerEntity entity : hasProducerEntity) {
            producerEntities.add(producerDao.findById(entity.getIdProducer()).orElseThrow());
        }
        return producerEntities;
    }

    public List<GenreEntity> getAnimeGenres(Long idAnime) {
        List<HasGenreEntity> hasGenreEntities = hasGenreDao.findAllByIdAnime(idAnime);
        List<GenreEntity> genreEntities = new ArrayList<>();
        for (HasGenreEntity entity : hasGenreEntities) {
            genreEntities.add(genreDao.findById(entity.getIdGenre()).orElseThrow());
        }
        return genreEntities;
    }
}
