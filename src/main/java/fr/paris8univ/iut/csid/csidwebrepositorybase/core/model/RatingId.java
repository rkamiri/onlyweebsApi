package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import java.io.Serializable;
import java.util.Objects;

public class RatingId implements Serializable {
    private Long userId;
    private Long animeId;

    public RatingId() {
    }

    public RatingId(Long userId, Long animeId) {
        this.userId = userId;
        this.animeId = animeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingId ratingId = (RatingId) o;
        return Objects.equals(userId, ratingId.userId) && Objects.equals(animeId, ratingId.animeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, animeId);
    }
}
