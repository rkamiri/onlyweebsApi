package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ArticleCategoriesDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ArticleCategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleCategoriesService {

    private final ArticleCategoriesRepository articleCategoriesRepository;

    public ArticleCategoriesService(ArticleCategoriesRepository articleCategoriesRepository) {
        this.articleCategoriesRepository = articleCategoriesRepository;
    }

    public List<ArticleCategoriesDto> findAll() {
        return this.articleCategoriesRepository.findAll().stream().map(ArticleCategoriesDto::new).collect(Collectors.toList());
    }
}
