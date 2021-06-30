package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.PegiRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.PegiDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PegiService {

    private final PegiRepository pegiRepository;

    public PegiService(PegiRepository pegiRepository) {
        this.pegiRepository = pegiRepository;
    }

    public List<PegiDto> findAll(){
        return this.pegiRepository.findAll().stream().map(PegiDto::new).collect(Collectors.toList());
    }
}
