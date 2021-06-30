package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.GenreDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ProducerDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Studio;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final HasStudioDao hasStudioDao;
    private final HasGenreDao hasGenreDao;
    private final HasProducerDao hasProducerDao;
    private final GenreRepository genreRepository;
    private final ProducerRepository producerRepository;
    private final StudioDao studioDao;
    private final PegiRepository pegiRepository;
    private static final Long HENTAI_PEGI_ID = 6L;

    public AnimeService(AnimeRepository animeRepository, HasStudioDao hasStudioDao, HasGenreDao hasGenreDao, HasProducerDao hasProducerDao, GenreRepository genreRepository, ProducerRepository producerRepository, StudioDao studioDao, PegiRepository pegiRepository) {
        this.animeRepository = animeRepository;
        this.hasStudioDao = hasStudioDao;
        this.hasGenreDao = hasGenreDao;
        this.hasProducerDao = hasProducerDao;
        this.genreRepository = genreRepository;
        this.producerRepository = producerRepository;
        this.studioDao = studioDao;
        this.pegiRepository = pegiRepository;
    }

    public List<AnimeDto> getAllAnimes(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        PegiEntity hentaiEntity = pegiRepository.findOneById(HENTAI_PEGI_ID);
        return animeRepository.findAllByPegiEntityNotLike(pageable, hentaiEntity).stream().map(AnimeDto::new).collect(Collectors.toList());
    }

    public int getNumberOfAnimes() {
        PegiEntity hentaiEntity = pegiRepository.findOneById(HENTAI_PEGI_ID);
        return this.animeRepository.countAnimeEntitiesByPegiEntityNotLike(hentaiEntity);
    }

    public AnimeDto getAnime(Long id) {
        return this.animeRepository.findById(id).map(AnimeDto::new).orElseThrow();
    }

    public List<AnimeDto> researchAnimes(String research) {
        PegiEntity hentaiEntity = pegiRepository.findOneById(HENTAI_PEGI_ID);
        return this.animeRepository.findTop15ByTitleContainingAndPegiEntityNotLike(research, hentaiEntity).stream().map(AnimeDto::new).collect(Collectors.toList());
    }

    public List<AnimeDto> researchAnimesPagination(String research, int page) {
        PegiEntity hentaiEntity = pegiRepository.findOneById(HENTAI_PEGI_ID);
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        return this.animeRepository.findByTitleContainingAndPegiEntityNotLike(pageable, research, hentaiEntity).stream().map(AnimeDto::new).collect(Collectors.toList());
    }

    public int getResearchCount(Long producer, Long studio, Long genre) {
        List<AnimeDto> animeDtos = this.animeRepository.findAllByCompleteResearchForCount(producer, studio, genre).stream().map(AnimeDto::new).collect(Collectors.toList());
        return animeDtos.size();
    }

    public int getResearchCount(String research){
        PegiEntity hentaiEntity = pegiRepository.findOneById(HENTAI_PEGI_ID);
        List<AnimeDto> animeDtos = this.animeRepository.findByTitleContainingAndPegiEntityNotLike(research, hentaiEntity).stream().map(AnimeDto::new).collect(Collectors.toList());
        return animeDtos.size();
    }

    public List<AnimeDto> researchAnimesPagination(Long producer, Long studio, Long genre, int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        return this.animeRepository.findAllByCompleteResearch(producer, studio, genre, pageable).stream().map(AnimeDto::new).collect(Collectors.toList());
    }

    public List<Studio> getAnimeStudios(Long idAnime) {
        List<HasStudioEntity> hasStudioEntity = hasStudioDao.findAllByIdAnime(idAnime);
        List<StudioEntity> studioEntities = new ArrayList<>();
        for (HasStudioEntity entity : hasStudioEntity) {
            studioEntities.add(studioDao.findById(entity.getIdStudio()).orElseThrow());
        }
        return studioEntities.stream().map(Studio::new).collect(Collectors.toList());
    }

    public List<ProducerDto> getAnimeProducers(Long idAnime) {
        List<HasProducerEntity> hasProducerEntity = hasProducerDao.findAllByIdAnime(idAnime);
        List<ProducerEntity> producerEntities = new ArrayList<>();
        for (HasProducerEntity entity : hasProducerEntity) {
            producerEntities.add(producerRepository.findById(entity.getIdProducer()).orElseThrow());
        }
        return producerEntities.stream().map(ProducerDto::new).collect(Collectors.toList());
    }

    public List<GenreDto> getAnimeGenres(Long idAnime) {
        List<HasGenreEntity> hasGenreEntities = hasGenreDao.findAllByIdAnime(idAnime);
        List<GenreEntity> genreEntities = new ArrayList<>();
        for (HasGenreEntity entity : hasGenreEntities) {
            genreEntities.add(genreRepository.findById(entity.getIdGenre()).orElseThrow());
        }
        return genreEntities.stream().map(GenreDto::new).collect(Collectors.toList());
    }

    public List<AnimeDto> getAllAnimes() {
        List<AnimeDto> animeDtoList = new ArrayList<>();
        for (AnimeEntity animeEntity : this.animeRepository.findAll()) {
            animeDtoList.add(new AnimeDto(animeEntity));
        }
        return animeDtoList;
    }

    public List<AnimeDto> getLatestAnimes() {
        LocalDate now = LocalDate.now();
        String temporary;
        int month = now.getMonthValue();
        int year = now.getYear();
        if (now.getMonthValue() < 10)
            temporary = "0" + month;
        else
            temporary = "" + month;
        PegiEntity hentaiEntity = pegiRepository.findOneById(HENTAI_PEGI_ID);
        return this.animeRepository.findTop15ByAiringIsContainingAndPegiEntityNotLikeOrderByAiringDesc("from " + year + "-" + temporary, hentaiEntity).stream().map(AnimeDto::new).collect(Collectors.toList());
    }
}
