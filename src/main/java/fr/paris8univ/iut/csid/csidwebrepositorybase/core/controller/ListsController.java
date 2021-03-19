package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Lists;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.ListsService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.PasAnimeException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.PasListException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Lists getOneList(@PathVariable(value = "id", required = true) Long listId) throws PasListException {
        return this.listService.getOneById(listId);
    }

    @GetMapping("/{id}/content")
    public List<Anime> getAnimesInList(@PathVariable(value = "id", required = true) Long listId) throws PasAnimeException {
        return this.listService.findAnimeOfList(listId);
    }
}
