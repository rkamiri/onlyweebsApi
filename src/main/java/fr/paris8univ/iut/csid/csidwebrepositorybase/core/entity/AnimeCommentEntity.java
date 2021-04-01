package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeCommentId;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "anime_comment")
@IdClass(AnimeCommentId.class)
public class AnimeCommentEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Id
    @Column(name = "anime_id", nullable = false)
    private Long anime_id;

    @Column(name = "comment")
    private String comment;

    public AnimeCommentEntity() {
    }

    public AnimeCommentEntity(Long user_id, Long anime_id, String comment) {
        this.user_id = user_id;
        this.anime_id = anime_id;
        this.comment = comment;
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

    public void setRate(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimeCommentEntity that = (AnimeCommentEntity) o;
        return comment.equals(that.comment) && Objects.equals(user_id, that.user_id) && Objects.equals(anime_id, that.anime_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, anime_id, comment);
    }

    @Override
    public String toString() {
        return "AnimeCommentEntity{" +
                "user_id=" + user_id +
                ", anime_id=" + anime_id +
                ", comment=" + comment +
                '}';
    }
}
