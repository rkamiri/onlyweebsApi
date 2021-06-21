package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.PegiDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Pegi;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PegiService {

    private final PegiDao pegiDao;

    public PegiService(PegiDao pegiDao) {
        this.pegiDao = pegiDao;
    }

    public List<Pegi> findAll(){
        return this.pegiDao.findAll().stream().map(Pegi::new).collect(Collectors.toList());
    }
}
