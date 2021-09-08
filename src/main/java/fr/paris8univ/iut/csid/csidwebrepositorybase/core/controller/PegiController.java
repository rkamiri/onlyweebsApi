package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.PegiDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.PegiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<PegiDto> getAllPegi() {
        return this.pegiService.findAll();
    }
}
