package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.IsListedInEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoAnimeException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoListException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ListsDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListsService {

    private final ListsRepository listsRepository;
    private final IsListedInRepository listedInRepository;
    private final AnimeRepository animeRepository;
    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ListsService(ListsRepository listsRepository, IsListedInRepository listedInRepository, AnimeRepository animeRepository, CommentRepository commentRepository, UsersRepository usersRepository) {
        this.listsRepository = listsRepository;
        this.listedInRepository = listedInRepository;
        this.animeRepository = animeRepository;
        this.commentRepository = commentRepository;
        this.usersRepository = usersRepository;
    }

    public List<ListsDto> getLists() {
        return this.listsRepository.findAll().stream().map(ListsDto::new).collect(Collectors.toList());
    }

    public List<ListsDto> getMyDefaultLists(long userId) {
        return this.listsRepository.getListsEntitiesByIsOwnedByAndIsDefault(this.usersRepository.getOne(userId), 1).stream().map(ListsDto::new).collect(Collectors.toList());
    }

    public List<ListsDto> getMyCustomLists(long userId) {
        return this.listsRepository.getListsEntitiesByIsOwnedByAndIsDefault(this.usersRepository.getOne(userId), 0).stream().map(ListsDto::new).collect(Collectors.toList());
    }

    public List<ListsEntity> getListsEntity() {
        return this.listsRepository.findAll();
    }

    public List<ListsEntity> getMyDefaultListsEntity(long userId) {
        return this.listsRepository.getListsEntitiesByIsOwnedByAndIsDefault(this.usersRepository.getOne(userId), 1);
    }

    public List<ListsEntity> getMyCustomListsEntity(long userId) {
        return this.listsRepository.getListsEntitiesByIsOwnedByAndIsDefault(this.usersRepository.getOne(userId), 0);
    }

    public ListsDto getOneById(Long id) throws NoListException {
        return this.listsRepository.findById(id).map(ListsDto::new).orElseThrow(NoListException::new);
    }

    public List<AnimeDto> findAnimeOfList(Long listId) throws NoAnimeException {
        List<AnimeDto> animeList = new ArrayList<>();
        List<IsListedInEntity> animesListedIn = this.listedInRepository.getByListId(listId);
        for (IsListedInEntity animeListed : animesListedIn)
            animeList.add(new AnimeDto(this.animeRepository.findOneById(animeListed.getAnimeId()).orElseThrow(NoAnimeException::new)));
        return animeList;
    }

    public void createList(ListsDto list, String currentUserLogin) throws NoUserFoundException {
        LocalDateTime now = LocalDateTime.now();
        this.listsRepository.save(
                new ListsEntity(list.getName(),
                        dtf.format(now),
                        list.getDescription(),
                        this.usersRepository.findByUsername(currentUserLogin).orElseThrow(NoUserFoundException::new),
                        0));
    }

    public void putAnimeInList(Long animeId, Long listId) {
        if (this.animeRepository.findOneById(animeId).isPresent() && this.listsRepository.findListById(listId).isPresent()) {
            boolean isInList = false;
            for (IsListedInEntity isListedInEntity : this.listedInRepository.findAll()) {
                if (isListedInEntity.getListId().equals(listId) && isListedInEntity.getAnimeId().equals(animeId)) {
                    isInList = true;
                    break;
                }
            }
            if (!isInList) {
                this.listedInRepository.save(new IsListedInEntity(listId, animeId));
            }
        }
    }

    public void deleteAnimeInList(Long anime_id, Long list_id) {
        if (this.animeRepository.findOneById(anime_id).isPresent() && this.listsRepository.findListById(list_id).isPresent()) {
            List<IsListedInEntity> ilel = this.listedInRepository.findAll();
            ilel.removeIf(e -> !e.getAnimeId().equals(anime_id) || !e.getListId().equals(list_id));
            this.listedInRepository.delete(ilel.get(0));
        }
    }

    @Transactional
    public void deleteList(long id) {
        this.listedInRepository.deleteIsListedInEntitiesByListId(id);
        this.commentRepository.deleteCommentEntitiesByListsEntity(this.listsRepository.getOne(id));
        this.listsRepository.deleteById(id);
    }

    public ListsDto getNewestList() {
        return new ListsDto(this.listsRepository.findFirstByOrderByIdDesc());
    }

    public ListsDto findListByNameAndUserId(String name, long userid) {
        return new ListsDto(this.listsRepository.findOneByNameAndIsOwnedBy(name, this.usersRepository.getOne(userid)));
    }

    public List<ListsDto> getCustomLists() {
        return this.listsRepository.findByIsDefault(0).stream().map(ListsDto::new).collect(Collectors.toList());
    }

    public List<List<String>> getFourImagesOfEachListAll() throws NoAnimeException {
        return this.getFourImagesOfEachList(this.listsRepository.findAll());
    }

    public List<List<String>> getFourImagesOfEachCustomList() throws NoAnimeException {
        List<ListsEntity> ilel = this.listsRepository.findAll();
        ilel.removeIf(e -> e.getIsDefault() == 1);
        return this.getFourImagesOfEachList(ilel);
    }

    public List<List<String>> getFourImagesOfEachCustomListUser(String currentUserLogin) throws NoAnimeException, NoUserFoundException {
        return this.getFourImagesOfEachList(this.getMyCustomListsEntity(this.usersRepository.findByUsername(currentUserLogin).orElseThrow(NoUserFoundException::new).getId()));
    }

    public List<List<String>> getFourImagesOfEachDefaultListUser(String currentUserLogin) throws NoAnimeException, NoUserFoundException {
        return this.getFourImagesOfEachList(this.getMyDefaultListsEntity(this.usersRepository.findByUsername(currentUserLogin).orElseThrow(NoUserFoundException::new).getId()));
    }

    public List<List<String>> getFourImagesOfEachCustomListByUserId(long id) throws NoAnimeException {
        return this.getFourImagesOfEachList(this.getMyCustomListsEntity(id));
    }

    public List<List<String>> getFourImagesOfEachList(List<ListsEntity> all) throws NoAnimeException {
        List<List<String>> fourImageUrlOfEachListInAList = new ArrayList<>();
        for (ListsEntity list : all) {
            List<AnimeDto> listOfAnime = this.findAnimeOfList(list.getId());
            List<String> imagesUrl = new ArrayList<>();
            if (listOfAnime.size() >= 4) {
                for (int i = 0; i < 4; i++) {
                    imagesUrl.add(listOfAnime.get(i).getImgUrl());
                }
            } else
                imagesUrl = listOfAnime.stream().map(AnimeDto::getImgUrl).collect(Collectors.toList());
            fourImageUrlOfEachListInAList.add(imagesUrl);
        }
        return fourImageUrlOfEachListInAList;
    }
}
