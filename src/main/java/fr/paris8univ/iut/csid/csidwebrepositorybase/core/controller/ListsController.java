package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.IsListedIn;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Lists;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.ListsService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoAnimeException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class ListsController {

    private final ListsService listService;

    @Autowired
    public ListsController(ListsService listService) {
        this.listService = listService;
    }

    @GetMapping
    public List<Lists> getLists() {
        return this.listService.getLists();
    }

    @GetMapping("/user/default/{id}")
    public List<Lists> getMyDefaultLists(@PathVariable long id) {
        return this.listService.getMyDefaultLists(id);
    }

    @GetMapping("/user/custom/{id}")
    public List<Lists> getMyCustomLists(@PathVariable long id) {
        return this.listService.getMyCustomLists(id);
    }

    @GetMapping("/{id}")
    public Lists getOneList(@PathVariable(value = "id") Long listId) throws NoListException {
        return this.listService.getOneById(listId);
    }

    @GetMapping("/{id}/content")
    public List<Anime> getAnimesInList(@PathVariable(value = "id") Long listId) throws NoAnimeException {
        return this.listService.findAnimeOfList(listId);
    }

    @PostMapping
    public ResponseEntity<Lists> createList(@RequestBody Lists list) throws URISyntaxException {
        listService.createList(list);
        URI location = new URI("/create-list/" + list.getName().replaceAll(" ", "_").toLowerCase());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Lists> putAnimeInList(@RequestBody IsListedIn ili) throws URISyntaxException {
        listService.putAnimeInList(ili.getAnime_id(), ili.getList_id());
        URI location = new URI("/put-in-list/" + ili.getAnime_id() + "_" + ili.getList_id());
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{listId}/{animeId}")
    public void deleteAnimeFromList(@PathVariable long animeId, @PathVariable long listId) {
        listService.deleteAnimeInList(animeId,listId);
    }

    @GetMapping("/getlastlist")
    public Lists getNewestList() {
        return this.listService.getNewestList();
    }

    @GetMapping("/{user_id}/{list_name}")
    public Lists findListByNameAndUserId(@PathVariable String list_name, @PathVariable long user_id) {
        return this.listService.findListByNameAndUserId(list_name, user_id);
    }
}
