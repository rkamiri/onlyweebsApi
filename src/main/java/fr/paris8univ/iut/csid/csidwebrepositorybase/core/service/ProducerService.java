package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ProducerDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Producer;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    private final ProducerDao producerDao;

    public ProducerService(ProducerDao producerDao) {
        this.producerDao = producerDao;
    }

    public List<Producer> findAll(){
        return this.producerDao.findAll().stream().map(Producer::new).collect(Collectors.toList());
    }
}
