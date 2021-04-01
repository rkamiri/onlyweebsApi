package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;

public class AnimeComment {

    private Long userId;
    private Long animeId;
    private String comment;

    public AnimeComment() {
    }

    public AnimeComment(AnimeCommentEntity re) {
        this.userId = re.getUser_id();
        this.animeId = re.getAnime_id();
        this.comment = re.getComment();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }

    public String getComment() {
        return comment;
    }

    public void setRate(String comment) {
        this.comment = comment;
    }
}
