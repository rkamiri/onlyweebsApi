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
    private Long user_id;

    @Id
    @Column(name = "anime_id", nullable = false)
    private Long anime_id;

    @Column(name = "rate")
    private Long rate;

    public RatingEntity() {
    }

    public RatingEntity(Long user_id, Long anime_id, Long rate) {
        this.user_id = user_id;
        this.anime_id = anime_id;
        this.rate = rate;
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
        return rate.equals(that.rate) && Objects.equals(user_id, that.user_id) && Objects.equals(anime_id, that.anime_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, anime_id, rate);
    }

    @Override
    public String toString() {
        return "RatingEntity{" +
                "user_id=" + user_id +
                ", anime_id=" + anime_id +
                ", rate=" + rate +
                '}';
    }
}
