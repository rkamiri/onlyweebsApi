package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;

public class AnimeComment {

    private Long user_id;
    private Long anime_id;
    private String comment;
    private String date;

    public AnimeComment() {
    }

    public AnimeComment(AnimeCommentEntity re) {
        this.user_id = re.getUser_id();
        this.anime_id = re.getAnime_id();
        this.comment = re.getComment();
        this.comment = re.getDate();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(Long anime_id) {
        this.anime_id = anime_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRate(String comment) {
        this.comment = comment;
    }
}
