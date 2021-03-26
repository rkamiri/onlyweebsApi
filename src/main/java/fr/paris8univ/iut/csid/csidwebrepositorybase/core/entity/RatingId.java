package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import java.io.Serializable;
import java.util.Objects;

public class RatingId implements Serializable {
    private Long user_id;
    private Long anime_id;

    public RatingId() {
    }

    public RatingId(Long userId, Long animeId) {
        this.user_id = userId;
        this.anime_id = animeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId ratingId = (RatingId) o;
        return Objects.equals(user_id, ratingId.user_id) && Objects.equals(anime_id, ratingId.anime_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, anime_id);
    }
}
