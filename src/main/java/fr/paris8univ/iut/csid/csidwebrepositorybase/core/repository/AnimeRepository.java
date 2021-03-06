package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.PegiDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AnimeRepository {

    private final AnimeDao animeDao;
    private final PegiDao pegiDao;
    private static final Long HENTAI_PEGI_ID = 6L;

    @Autowired
    public AnimeRepository(AnimeDao adao, PegiDao pegiDao) {
        this.animeDao = adao;
        this.pegiDao = pegiDao;
    }

    public List<AnimeEntity> findAllAnime(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        return animeDao.findAllByPegiEntityNotLike(pageable, hentaiEntity).toList();
    }

    public int getCount() {
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        return this.animeDao.countAnimeEntitiesByPegiEntityNotLike(hentaiEntity);
    }

    public Optional<AnimeEntity> findOneAnime(Long id) {
        return this.animeDao.findById(id);
    }

    public List<Anime> researchAnimes(String research) {
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        return this.animeDao.findTop15ByTitleContainingAndPegiEntityNotLike(research, hentaiEntity).stream().map(Anime::new).collect(Collectors.toList());
    }

    public List<Anime> researchAnimesPagination(String research, int page) {
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        return this.animeDao.findByTitleContainingAndPegiEntityNotLike(pageable, research, hentaiEntity).stream().map(Anime::new).collect(Collectors.toList());
    }

    public int getResearchCount(String research) {
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        List<Anime> animes = this.animeDao.findByTitleContainingAndPegiEntityNotLike(research, hentaiEntity).stream().map(Anime::new).collect(Collectors.toList());
        return animes.size();
    }

    public int getResearchCount(Long producer, Long studio, Long genre) {
        List<Anime> animes = this.animeDao.findAllByCompleteResearchForCount(producer, studio, genre).stream().map(Anime::new).collect(Collectors.toList());
        return animes.size();
    }

    public List<Anime> researchAnimesPagination(Long producer, Long studio, Long genre, int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        return this.animeDao.findAllByCompleteResearch(producer, studio, genre, pageable).stream().map(Anime::new).collect(Collectors.toList());
    }

    public List<Anime> getLatestAnimes() {
        LocalDate now = LocalDate.now();
        String temporary;
        int month = now.getMonthValue();
        int year = now.getYear();
        if (now.getMonthValue() < 10)
            temporary = "0" + month;
        else
            temporary = "" + month;
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        return this.animeDao.findTop15ByAiringIsContainingAndPegiEntityNotLikeOrderByAiringDesc("from " + year + "-" + temporary, hentaiEntity).stream().map(Anime::new).collect(Collectors.toList());
    }
}
