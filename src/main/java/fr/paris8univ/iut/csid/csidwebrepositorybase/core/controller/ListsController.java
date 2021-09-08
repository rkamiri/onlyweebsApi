package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoAnimeException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoListException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.IsListedInDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ListsDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.ListsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/lists")
public class ListsController {

    private final ListsService listService;

    @Autowired
    public ListsController(ListsService listService) {
        this.listService = listService;
    }

    @GetMapping
    public ResponseEntity<List<ListsDto>> getLists() {
        CacheControl cacheControl = CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate();
        List<ListsDto> content = this.listService.getLists();
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }

    @GetMapping("/user/default/{id}")
    public List<ListsDto> getMyDefaultLists(@PathVariable long id) {
        return this.listService.getMyDefaultLists(id);
    }

    @GetMapping("/user/custom/{id}")
    public List<ListsDto> getMyCustomLists(@PathVariable long id) {
        return this.listService.getMyCustomLists(id);
    }

    @GetMapping("/{id}")
    public ListsDto getOneList(@PathVariable(value = "id") Long listId) throws NoListException {
        return this.listService.getOneById(listId);
    }

    @GetMapping("/{id}/content")
    public List<AnimeDto> getAnimesInList(@PathVariable(value = "id") Long listId) throws NoAnimeException {
        return this.listService.findAnimeOfList(listId);
    }

    @PostMapping
    public ResponseEntity<ListsDto> createList(@RequestBody ListsDto list) throws URISyntaxException, NotFoundException, NoUserFoundException {
        listService.createList(list, UserController.getCurrentUserLogin());
        URI location = new URI("/create-list/" + list.getName().replaceAll(" ", "_").toLowerCase());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ListsDto> putAnimeInList(@RequestBody IsListedInDto ili) throws URISyntaxException {
        listService.putAnimeInList(ili.getAnime_id(), ili.getList_id());
        URI location = new URI("/put-in-list/" + ili.getAnime_id() + "_" + ili.getList_id());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{listId}/{animeId}")
    public void deleteAnimeFromList(@PathVariable long animeId, @PathVariable long listId) {
        listService.deleteAnimeInList(animeId, listId);
    }

    @GetMapping("/getlastlist")
    public ListsDto getNewestList() {
        return this.listService.getNewestList();
    }

    @GetMapping("/{user_id}/{list_name}")
    public ListsDto findListByNameAndUserId(@PathVariable String list_name, @PathVariable long user_id) {
        return this.listService.findListByNameAndUserId(list_name, user_id);
    }

    @GetMapping("/custom")
    public ResponseEntity<List<ListsDto>> getCustomLists() {
        List<ListsDto> content = this.listService.getCustomLists();
        MediaType contentType = MediaType.valueOf("application/json");
        return ResponseEntity.status(200).contentType(contentType).body(content);
    }

    @GetMapping("/spotify/image")
    public List<List<String>> getFourImagesOfEachListAll() throws NoAnimeException {
        return this.listService.getFourImagesOfEachListAll();
    }

    @GetMapping("/spotify/image/custom")
    public List<List<String>> getFourImagesOfEachCustomList() throws NoAnimeException {
        return this.listService.getFourImagesOfEachCustomList();
    }

    @GetMapping("/user/image/default")
    public List<List<String>> getFourImagesOfEachDefaultListUser() throws NotFoundException, NoAnimeException, NoUserFoundException {
        return this.listService.getFourImagesOfEachDefaultListUser(UserController.getCurrentUserLogin());
    }

    @GetMapping("/user/image/custom")
    public List<List<String>> getFourImagesOfEachCustomListUser() throws NotFoundException, NoAnimeException, NoUserFoundException {
        return this.listService.getFourImagesOfEachCustomListUser(UserController.getCurrentUserLogin());
    }

    @GetMapping("/user/{id}/image/custom")
    public List<List<String>> getFourImagesOfEachCustomListByUserId(@PathVariable long id) throws NoAnimeException {
        return this.listService.getFourImagesOfEachCustomListByUserId(id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteList(@PathVariable long id) {
        this.listService.deleteList(id);
    }
}
