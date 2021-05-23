package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.AnimeService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.PegiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/pegi")
public class PegiController {

    private final PegiService pegiService;

    @Autowired
    public PegiController(PegiService pegiService) {
        this.pegiService = pegiService;
    }

    @GetMapping("/all")
    public List<PegiEntity> getPegis() {
        return this.pegiService.findAll();
    }
}
