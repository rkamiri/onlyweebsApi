package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleCategoriesEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import lombok.Data;

@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String body;
    private String created_at;
    private UsersEntity author;
    private ImageEntity cover;
    private ArticleCategoriesEntity category;

    public ArticleDto(Long id, String title, String body, String created_at, UsersEntity author, ImageEntity cover, ArticleCategoriesEntity category) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.created_at = created_at;
        this.author = author;
        this.cover = cover;
        this.category = category;
    }

    public ArticleDto(ArticleEntity articleEntity) {
        this.id = articleEntity.getId();
        this.title = articleEntity.getTitle();
        this.body = articleEntity.getBody();
        this.created_at = articleEntity.getCreated_at();
        this.author = articleEntity.getAuthor();
        this.cover = articleEntity.getCover();
        this.category = articleEntity.getCategory();
    }
}
