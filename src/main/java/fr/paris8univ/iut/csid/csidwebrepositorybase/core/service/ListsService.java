package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoAnimeException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoListException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Lists;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ListsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListsService {

    private final ListsRepository listsRepository;

    public ListsService(ListsRepository listsRepository) {
        this.listsRepository = listsRepository;
    }

    public List<Lists> getLists() {
        return this.listsRepository.getLists();
    }

    public List<Lists> getMyDefaultLists(long id) {
        return this.listsRepository.getMyDefaultLists(id).stream().map(Lists::new).collect(Collectors.toList());
    }

    public List<Lists> getMyCustomLists(long id) {
        return this.listsRepository.getMyCustomLists(id).stream().map(Lists::new).collect(Collectors.toList());
    }

    public Lists getOneById(Long id) throws NoListException {
        return this.listsRepository.findListById(id).orElseThrow(NoListException::new);
    }

    public List<Anime> findAnimeOfList(Long listId) throws NoAnimeException {
        List<Anime> realAnimeList = new ArrayList<>();
        List<Optional<Anime>> al = this.listsRepository.findAnimeOfList(listId);
        for (Optional<Anime> fakeAnime : al) {
            realAnimeList.add(fakeAnime.orElseThrow(NoAnimeException::new));
        }
        return realAnimeList;
    }

    public void createList(Lists list, String currentUserLogin) {
        this.listsRepository.createList(list, currentUserLogin);
    }

    public void putAnimeInList(Long animeId, Long listId) {
        this.listsRepository.putAnimeInList(animeId, listId);
    }

    public void deleteAnimeInList(Long anime_id, Long list_id) {
        this.listsRepository.deleteAnimeInList(anime_id, list_id);
    }

    public Lists getNewestList() {
        return this.listsRepository.getNewestList();
    }

    public Lists findListByNameAndUserId(String name, long userid) {
        return this.listsRepository.findListByNameAndUserId(name, userid);
    }

    public List<Lists> getCustomLists() {
        return this.listsRepository.getCustomLists().stream().map(Lists::new).collect(Collectors.toList());
    }

    public List<ListsEntity> getCustomEntities() {
        return this.listsRepository.getCustomLists();
    }

    public List<List<String>> getFourImagesOfEachListAll(){
        return this.listsRepository.getFourImagesForAllLists();
    }

    public List<List<String>> getFourImagesOfEachCustomList(){
        return this.listsRepository.getFourImagesForEachCustomList();
    }

    public void deleteList(long id) {
        this.listsRepository.deleteList(id);
    }

    public List<List<String>> getFourImagesOfEachCustomListUser(String currentUserLogin) {
        return this.listsRepository.getFourImagesOfEachCustomListUser(currentUserLogin);
    }

    public List<List<String>> getFourImagesOfEachDefaultListUser(String currentUserLogin) {
        return this.listsRepository.getFourImagesOfEachDefaultListUser(currentUserLogin);
    }
}
