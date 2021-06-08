package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;

public class Comment {

    private Long id;
    private Users user;
    private String body;
    private String date;
    private AnimeEntity animeEntity;
    private ArticleEntity articleEntity;
    private ListsEntity listsEntity;

    public Comment() {
    }

    public Comment(CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.user = new Users(commentEntity.getUsersEntity());
        this.body = commentEntity.getBody();
        this.date = commentEntity.getDate();
        this.animeEntity = commentEntity.getAnimeEntity();
        this.articleEntity = commentEntity.getArticleEntity();
        this.listsEntity = commentEntity.getListsEntity();

    }

    public ArticleEntity getArticleEntity() {
        return articleEntity;
    }

    public Long getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public AnimeEntity getAnimeEntity() {
        return animeEntity;
    }

    public String getDate() {
        return date;
    }

    public ListsEntity getListsEntity() {
        return listsEntity;
    }

    public void setListsEntity(ListsEntity listsEntity) {
        this.listsEntity = listsEntity;
    }
}
