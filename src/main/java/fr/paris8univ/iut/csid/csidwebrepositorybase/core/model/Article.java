package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleCategoriesEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;

public class Article {
    private Long id;
    private String title;
    private String body;
    private String created_at;
    private UsersEntity author;
    private ImageEntity cover;
    private ArticleCategoriesEntity category;

    public Article(Long id, String title, String body, String created_at, UsersEntity author, ImageEntity cover,  ArticleCategoriesEntity category) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.created_at = created_at;
        this.author = author;
        this.cover = cover;
        this.category = category;
    }

    public Article(ArticleEntity articleEntity) {
        this.id = articleEntity.getId();
        this.title = articleEntity.getTitle();
        this.body = articleEntity.getBody();
        this.created_at = articleEntity.getCreated_at();
        this.author = articleEntity.getAuthor();
        this.cover = articleEntity.getCover();
        this.category = articleEntity.getCategory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public UsersEntity getAuthor() {
        return author;
    }

    public void setAuthor(UsersEntity author) {
        this.author = author;
    }

    public ImageEntity getCover() {
        return cover;
    }

    public void setCover(ImageEntity cover) {
        this.cover = cover;
    }

    public ArticleCategoriesEntity getCategory() {
        return category;
    }

    public void setCategory(ArticleCategoriesEntity category) {
        this.category = category;
    }
}
