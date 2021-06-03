package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleCategoriesEntity;

public class ArticleCategories {
    private Long id;
    private String name;
    private String bgColor;

    public ArticleCategories(ArticleCategoriesEntity articleCategoriesEntity) {
        this.id = articleCategoriesEntity.getId();
        this.name = articleCategoriesEntity.getName();
        this.bgColor = articleCategoriesEntity.getBgColor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
