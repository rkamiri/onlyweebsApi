package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.StudioDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.StudioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioService {

    private final StudioRepository studioRepository;

    public StudioService(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public List<StudioDto> findAll() {
        return this.studioRepository.findAll().stream().map(StudioDto::new).collect(Collectors.toList());
    }
}
