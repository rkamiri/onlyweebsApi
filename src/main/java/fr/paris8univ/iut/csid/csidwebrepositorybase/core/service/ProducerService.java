package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.HasProducerDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.PegiDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ProducerDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ProducerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {

    private final ProducerDao producerDao;

    public ProducerService(ProducerDao producerDao) {
        this.producerDao = producerDao;
    }

    public List<ProducerEntity> findAll(){
        return this.producerDao.findAll();
    }
}
