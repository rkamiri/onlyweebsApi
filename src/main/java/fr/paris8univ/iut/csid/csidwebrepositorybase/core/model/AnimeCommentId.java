package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AnimeCommentId implements Serializable {
    private Long user_id;
    private Long anime_id;

    public AnimeCommentId() {
    }

    public AnimeCommentId(Long userId, Long animeId) {
        this.user_id = userId;
        this.anime_id = animeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimeCommentId animeCommentId = (AnimeCommentId) o;
        return Objects.equals(user_id, animeCommentId.user_id) && Objects.equals(anime_id, animeCommentId.anime_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, anime_id);
    }
}
