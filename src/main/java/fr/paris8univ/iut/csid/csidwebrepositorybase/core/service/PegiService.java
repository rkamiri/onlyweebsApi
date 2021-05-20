package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.PegiDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PegiService {

    private final PegiDao pegiDao;

    public PegiService(PegiDao pegiDao) {
        this.pegiDao = pegiDao;
    }

    public List<PegiEntity> findAll(){
        return this.pegiDao.findAll();
    }
}
