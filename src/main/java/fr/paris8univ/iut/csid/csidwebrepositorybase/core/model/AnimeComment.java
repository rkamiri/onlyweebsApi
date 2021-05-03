package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;

public class AnimeComment {

    private Users user;
    private Long anime_id;
    private String comment;

    public AnimeComment() {
    }

    public AnimeComment(AnimeCommentEntity re) {
        this.user = new Users(re.getUsersEntity());
        this.anime_id = re.getAnime_id();
        this.comment = re.getComment();
        this.comment = re.getDate();
    }

    public Users getUser() {
        return user;
    }

    public Long getAnime_id() {
        return anime_id;
    }

    public String getComment() {
        return comment;
    }
}
