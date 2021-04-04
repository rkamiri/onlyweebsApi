package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.HasImageRepository;
import org.springframework.stereotype.Service;

@Service
public class HasImageService {

    private final HasImageRepository hasImageRepository;

    public HasImageService(HasImageRepository hasImageRepository) {
        this.hasImageRepository = hasImageRepository;
    }

    public Long getImageId(Long id) {
        return this.hasImageRepository.getImageId(id);
    }
}
