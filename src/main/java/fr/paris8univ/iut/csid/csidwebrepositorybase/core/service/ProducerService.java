package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ProducerDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public List<ProducerDto> findAll() {
        return this.producerRepository.findAll().stream().map(ProducerDto::new).collect(Collectors.toList());
    }
}
