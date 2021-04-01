package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Anime> findAllAnime() {
        List<AnimeEntity> entities = this.animeDao.findAll();
        return entities.stream().map(Anime::new).collect(Collectors.toList());
    }

    public Optional<Anime> findOneAnime(Long id) {
        return this.animeDao.findById(id).map(Anime::new);
    }

    public List<Anime> researchAnimes(String research) {
        return this.animeDao.findByInternationalTitleContainingOrTitleContaining(research, research).stream().map(Anime::new).collect(Collectors.toList());
    }
}
