package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleCategoriesEntity;
import lombok.Data;

@Data
public class ArticleCategoriesDto {
    private Long id;
    private String name;
    private String bgColor;

    public ArticleCategoriesDto(ArticleCategoriesEntity articleCategoriesEntity) {
        this.id = articleCategoriesEntity.getId();
        this.name = articleCategoriesEntity.getName();
        this.bgColor = articleCategoriesEntity.getBgColor();
    }
}
