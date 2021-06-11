package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.RatingId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rating")
@IdClass(RatingId.class)
public class RatingEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "anime_id", nullable = false)
    private Long anime_id;

    @Column(name = "rate")
    private Long rate;

    public RatingEntity() {
    }

    public RatingEntity(Long userId, Long anime_id, Long rate) {
        this.userId = userId;
        this.anime_id = anime_id;
        this.rate = rate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public Long getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(Long anime_id) {
        this.anime_id = anime_id;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingEntity that = (RatingEntity) o;
        return rate.equals(that.rate) && Objects.equals(userId, that.userId) && Objects.equals(anime_id, that.anime_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, anime_id, rate);
    }

    @Override
    public String toString() {
        return "RatingEntity{" +
                "user_id=" + userId +
                ", anime_id=" + anime_id +
                ", rate=" + rate +
                '}';
    }
}
