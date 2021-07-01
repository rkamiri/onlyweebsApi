package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import lombok.Data;

@Data
public class ArticleResearchDto {
    public String title;
    public Integer categoryId;

    public ArticleResearchDto(String title, Integer categoryId) {
        this.title = title;
        this.categoryId = categoryId;
    }
}
