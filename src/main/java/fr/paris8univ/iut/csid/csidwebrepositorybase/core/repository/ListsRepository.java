package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.IsListedInDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ListsDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ListsRepository {

    private final ListsDao listsDao;
    private final IsListedInDao listedInDao;
    private final AnimeRepository animeRepository;

    @Autowired
    public ListsRepository(ListsDao listsDao, IsListedInDao listedInDao, AnimeRepository animeRepository) {
        this.listsDao = listsDao;
        this.listedInDao = listedInDao;
        this.animeRepository = animeRepository;
    }

    public List<Lists> getLists() {
        List<ListsEntity> listsEntityList = this.listsDao.findAll();
        return listsEntityList.stream().map(Lists::new).collect(Collectors.toList());
    }

    public Optional<Lists> findListById(Long id) {
        return this.listsDao.findById(id).map(Lists::new);
    }

    public List<Optional<Anime>> findAnimeOfList(Long listId) {
        List<Optional<Anime>> al = new ArrayList<>();
        List<IsListedInEntity> x = this.listedInDao.findAll();
        for ( IsListedInEntity s : x) {
            if (s.getList_id().equals(listId)) {
                al.add(this.animeRepository.findOneAnime(s.getAnime_id()));
            }
        }
        return al;
    }
}
