package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.StudioDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Studio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioService {

    private final StudioDao studioDao;

    public StudioService(StudioDao studioDao) {
        this.studioDao = studioDao;
    }

    public List<Studio> findAll(){
        return this.studioDao.findAll().stream().map(Studio::new).collect(Collectors.toList());
    }
}
