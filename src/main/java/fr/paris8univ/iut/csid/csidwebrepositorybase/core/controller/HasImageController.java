package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.HasImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "hasimage")
public class HasImageController {

    private final HasImageService hasImageService;

    @Autowired
    public HasImageController(HasImageService hasImageService) {
        this.hasImageService = hasImageService;
    }

    @GetMapping("/{id}")
    public Long getImageId(@PathVariable Long id) {
        return this.hasImageService.getImageId(id);
    }
}
