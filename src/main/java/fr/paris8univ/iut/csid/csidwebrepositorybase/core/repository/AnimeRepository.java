package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
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

    @Autowired
    public AnimeRepository(AnimeDao adao) {
        this.animeDao = adao;
    }

    public List<Anime> findAllAnime(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");

        Page<AnimeEntity> entities = this.animeDao.findByGenreNotContaining(pageable, "hentai");
        return entities.stream().map(Anime::new).collect(Collectors.toList());
    }

    public int getCount(){
        return this.animeDao.findByGenreNotContaining("hentai").size();
    }

    public Optional<Anime> findOneAnime(Long id) {
        return this.animeDao.findById(id).map(Anime::new);
    }

    public List<Anime> researchAnimes(String research) {
        return this.animeDao.findTop15ByTitleContainingAndGenreNotContaining(research, "Hentai").stream().map(Anime::new).collect(Collectors.toList());
    }

    public List<Anime> researchAnimesPagination(String research, int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.Direction.DESC, "id");
        Page<AnimeEntity> entities = this.animeDao.findByTitleContainingAndGenreNotContaining(pageable, research, "hentai");
        return entities.stream().map(Anime::new).collect(Collectors.toList());
    }

    public int getResearchCount(String research){
        return this.animeDao.findByTitleContainingAndGenreNotContaining(research, "hentai").size();
    }


}
