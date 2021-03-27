package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;

public class Rating {

    private Long userId;
    private Long animeId;
    private Long rate;

    public Rating() {
    }

    public Rating(RatingEntity re) {
        this.userId = re.getUser_id();
        this.animeId = re.getAnime_id();
        this.rate = re.getRate();
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

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }
}
