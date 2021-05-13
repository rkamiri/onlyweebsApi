package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.IsListedInDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ListsDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    private final UsersRepository usersRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public ListsRepository(ListsDao listsDao, IsListedInDao listedInDao, AnimeRepository animeRepository, UsersRepository usersRepository) {
        this.listsDao = listsDao;
        this.listedInDao = listedInDao;
        this.animeRepository = animeRepository;
        this.usersRepository = usersRepository;
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
            if (s.getListId().equals(listId)) {
                al.add(this.animeRepository.findOneAnime(s.getAnime_id()));
            }
        }
        return al;
    }

    public void createList(Lists list, String currentUserLogin) {
        LocalDateTime now = LocalDateTime.now();
        this.listsDao.save(
                new ListsEntity(list.getName(),
                        dtf.format(now),
                        list.getDescription(),
                        this.usersRepository.findUserEntityByUsername(currentUserLogin).getId(),
                        0));
    }

    public void putAnimeInList(Long animeId, Long listId) {
        if (this.animeRepository.findOneAnime(animeId).isPresent() && this.findListById(listId).isPresent()) {
            boolean isInList = false;
            for (IsListedInEntity isListedInEntity : this.listedInDao.findAll()) {
                if (isListedInEntity.getListId().equals(listId) && isListedInEntity.getAnime_id().equals(animeId)) {
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
            ilel.removeIf(e -> !e.getAnime_id().equals(anime_id) || !e.getListId().equals(list_id));
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
        ilel.removeIf(e -> e.getIs_default() == 1);
        ilel.removeIf(e -> !e.getIs_owned_by().equals(id));
        return ilel.stream().map(Lists::new).collect(Collectors.toList());
    }

    public List<Lists> getMyDefaultLists(long id) {
        List<ListsEntity> ilel = this.listsDao.findAll();
        ilel.removeIf(e -> e.getIs_default() == 0);
        ilel.removeIf(e -> !e.getIs_owned_by().equals(id));
        return ilel.stream().map(Lists::new).collect(Collectors.toList());
    }

    public List<Lists> getCustomLists() {
        List<ListsEntity> ilel = this.listsDao.findAll();
        ilel.removeIf(e -> e.getIs_default() == 1);
        return ilel.stream().map(Lists::new).collect(Collectors.toList());
    }

    public List<List<String>> getFourImagesOfEachList(List<ListsEntity> all) {
        List<List<String>> fourImageUrlOfEachListInAList = new ArrayList<>();
        for (ListsEntity list : all) {
            List<Optional<Anime>> listOfAnime = findAnimeOfList(list.getId());
            List<String> imagesUrl = new ArrayList<>();
            if (listOfAnime.size() >= 4) {
                for (int i = 0; i < 4; i++) {
                    if (listOfAnime.get(i).isPresent())
                        imagesUrl.add(listOfAnime.get(i).get().getCover());
                }
            } else
                imagesUrl = listOfAnime.stream().map(e -> e.orElseThrow().getCover()).collect(Collectors.toList());
            fourImageUrlOfEachListInAList.add(imagesUrl);
        }
        return fourImageUrlOfEachListInAList;
    }

    public List<List<String>> getFourImagesForEachCustomList() {
        List<ListsEntity> ilel = this.listsDao.findAll();
        ilel.removeIf(e -> e.getIs_default() == 1);
        return this.getFourImagesOfEachList(ilel);
    }

    public List<List<String>> getFourImagesForAllLists() {
        return this.getFourImagesOfEachList(this.listsDao.findAll());
    }

    @Transactional
    public void deleteList(long id) {
        this.listedInDao.deleteIsListedInEntitiesByListId(id);
        this.listsDao.deleteById(id);
    }
}
