package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
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
    public List<Lists>getLists() {
        return this.listService.getLists();
    }

    @GetMapping("/{id}")
    public Lists getOneList(@PathVariable(value = "id", required = true) Long listId) throws NoListException {
        return this.listService.getOneById(listId);
    }

    @GetMapping("/{id}/content")
    public List<Anime> getAnimesInList(@PathVariable(value = "id", required = true) Long listId) throws NoAnimeException {
        return this.listService.findAnimeOfList(listId);
    }

    @PostMapping
    public ResponseEntity<Lists> createList(@RequestBody Lists list) throws URISyntaxException {
        listService.createList(list);
        URI location = new URI("/create-list/" + list.getName());
        return ResponseEntity.created(location).build();
    }
}
