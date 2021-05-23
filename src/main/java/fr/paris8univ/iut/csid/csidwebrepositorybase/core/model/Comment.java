package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;

public class Comment {

    private Long id;
    private Users user;
    private String body;
    private String date;
    private AnimeEntity animeEntity;

    public Comment() {
    }

    public Comment(CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.user = new Users(commentEntity.getUsersEntity());
        this.body = commentEntity.getBody();
        this.date = commentEntity.getDate();
        this.animeEntity = commentEntity.getAnimeEntity();
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
}
