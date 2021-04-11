package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.IsListedInDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ListsDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ListsRepository {

    private final ListsDao listsDao;
    private final IsListedInDao listedInDao;
    private final AnimeRepository animeRepository;
    private final
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    public Lists findListByNameAndUserId(String name, long userid) {
        List<ListsEntity> ilel = this.listsDao.findAll();
        ilel.removeIf(e -> !e.getName().equals(name) || !e.getIs_owned_by().equals(userid));
        return ilel.stream().map(Lists::new).collect(Collectors.toList()).get(0);
    }

    public List<Optional<Anime>> findAnimeOfList(Long listId) {
        List<Optional<Anime>> al = new ArrayList<>();
        List<IsListedInEntity> x = this.listedInDao.findAll();
        for (IsListedInEntity s : x) {
            if (s.getList_id().equals(listId)) {
                al.add(this.animeRepository.findOneAnime(s.getAnime_id()));
            }
        }
        return al;
    }

    public void createList(Lists list) {
        LocalDateTime now = LocalDateTime.now();
        this.listsDao.save(new ListsEntity(list.getName(), dtf.format(now), list.getDescription(), list.getIsOwnedBy()));
    }

    public void putAnimeInList(Long animeId, Long listId) {
        if (this.animeRepository.findOneAnime(animeId).isPresent() && this.findListById(listId).isPresent()) {
            boolean isInList = false;
            for (IsListedInEntity isListedInEntity : this.listedInDao.findAll()) {
                if (isListedInEntity.getList_id().equals(listId) && isListedInEntity.getAnime_id().equals(animeId)) {
                    isInList = true;
                    break;
                }
            }
            if (!isInList) {
                this.listedInDao.save(new IsListedInEntity(listId, animeId));
            }
        }
    }

    public void deleteAnimeInList(Long anime_id, Long list_id) {
        if (this.animeRepository.findOneAnime(anime_id).isPresent() && this.findListById(list_id).isPresent()) {
            List<IsListedInEntity> ilel = this.listedInDao.findAll();
            ilel.removeIf(e -> !e.getAnime_id().equals(anime_id) || !e.getList_id().equals(list_id));
            this.listedInDao.delete(ilel.get(0));
        }
    }

    public Lists getNewestList() {
        Long i = -2L;
        for (ListsEntity le : this.listsDao.findAll()) {
            if (le.getId() > i) {
                i = le.getId();
            }
        }
        return this.findListById(i).orElseGet(Lists::new);
    }

    public List<Lists> getMyCustomLists(long id) {
        List<ListsEntity> ilel = this.listsDao.findAll();
        ilel.removeIf(e -> e.getName().equals("Watched") ||
                e.getName().equals("Currently watching") ||
                e.getName().equals("Plan to watch"));
        ilel.removeIf(e -> !e.getIs_owned_by().equals(id));
        return ilel.stream().map(Lists::new).collect(Collectors.toList());
    }

    public List<Lists> getMyDefaultLists(long id) {
        List<ListsEntity> ilel = this.listsDao.findAll();
        ilel.removeIf(e -> !(e.getName().equals("Watched") ||
                e.getName().equals("Currently watching") ||
                e.getName().equals("Plan to watch")));
        ilel.removeIf(e -> !e.getIs_owned_by().equals(id));
        return ilel.stream().map(Lists::new).collect(Collectors.toList());
    }
}
