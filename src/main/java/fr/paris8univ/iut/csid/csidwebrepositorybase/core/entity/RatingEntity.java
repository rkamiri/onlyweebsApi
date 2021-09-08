package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.RatingId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "rating")
@IdClass(RatingId.class)
public class RatingEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Id
    @Column(name = "anime_id", nullable = false)
    private Long animeId;

    @Column(name = "rate")
    private Long rate;

    public RatingEntity() {
    }

    public RatingEntity(Long userId, Long animeId, Long rate) {
        this.userId = userId;
        this.animeId = animeId;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RatingEntity{" +
                "user_id=" + userId +
                ", anime_id=" + animeId +
                ", rate=" + rate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RatingEntity that = (RatingEntity) o;
        return Objects.equals(userId, that.userId)
                && Objects.equals(animeId, that.animeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, animeId);
    }
}
