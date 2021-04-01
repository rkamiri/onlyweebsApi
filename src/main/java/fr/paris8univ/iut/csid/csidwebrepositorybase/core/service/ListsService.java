package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Lists;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ListsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListsService {

    private final ListsRepository listsRepository;

    public ListsService(ListsRepository listsRepository) {
        this.listsRepository = listsRepository;
    }

    public List<Lists> getLists() {
        return this.listsRepository.getLists();
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

    public void createList(Lists list) {
        this.listsRepository.createList(list);
    }
 }
