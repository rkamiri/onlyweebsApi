package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.PegiDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    private static final Long HENTAI_PEGI_ID  = 166L;

    @Autowired
    public AnimeRepository(AnimeDao adao, PegiDao pegiDao) {
        this.animeDao = adao;
        this.pegiDao = pegiDao;
    }

    public List<Anime> findAllAnime(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        List<Anime> animes = this.animeDao.findAll(pageable).stream().map(Anime::new).collect(Collectors.toList());
        removeHentais(animes);
        return animes;
    }

    public int getCount(){
        List<Anime> animes = this.animeDao.findAll().stream().map(Anime::new).collect(Collectors.toList());
        removeHentais(animes);
        return animes.size();
    }

    public Optional<Anime> findOneAnime(Long id) {
        return this.animeDao.findById(id).map(Anime::new);
    }

    public List<Anime> researchAnimes(String research) {
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        List<Anime> animes =  this.animeDao.findTop15ByTitleContaining(research).stream().map(Anime::new).collect(Collectors.toList());
        removeHentais(animes);
        return animes;
    }

    public List<Anime> researchAnimesPagination(String research, int page) {
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        List<Anime> animes = this.animeDao.findByTitleContaining(pageable, research).stream().map(Anime::new).collect(Collectors.toList());
        removeHentais(animes);
        return animes;
    }

    public int getResearchCount(String research){
        PegiEntity hentaiEntity = pegiDao.findOneById(HENTAI_PEGI_ID);
        List<Anime> animes = this.animeDao.findByTitleContaining(research).stream().map(Anime::new).collect(Collectors.toList());
        removeHentais(animes);
        return animes.size();
    }

    private static void removeHentais(List<Anime> animes){
        animes.removeIf(anime -> anime.getPegi().getId().equals(HENTAI_PEGI_ID));
    }

}
