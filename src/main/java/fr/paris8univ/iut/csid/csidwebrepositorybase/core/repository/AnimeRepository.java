package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.PegiDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

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

    public List<Anime> findAllAnime(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.ASC, "id");
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        System.out.println(hentaiEntity.getName());
        return animeDao.findAllByPegiEntityNotLike(pageable, hentaiEntity).stream().map(Anime::new).collect(Collectors.toList());
    }

    public int getCount() {
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        List<Anime> animes = this.animeDao.findAllByPegiEntityNotLike(hentaiEntity).stream().map(Anime::new).collect(Collectors.toList());
        return animes.size();
    }

    public Optional<Anime> findOneAnime(Long id) {
        return this.animeDao.findById(id).map(Anime::new);
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

}
