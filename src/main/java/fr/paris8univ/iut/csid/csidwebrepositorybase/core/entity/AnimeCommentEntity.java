package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "anime_comment")
public class AnimeCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @Column(name = "anime_id", nullable = false)
    private Long anime_id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "post_date")
    private String date;

    public AnimeCommentEntity() {
    }

    public AnimeCommentEntity(Long user_id, Long anime_id, String comment, String date) {
        this.user_id = user_id;
        this.anime_id = anime_id;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "AnimeCommentEntity{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", anime_id=" + anime_id +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimeCommentEntity that = (AnimeCommentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(user_id, that.user_id) && Objects.equals(anime_id, that.anime_id) && Objects.equals(comment, that.comment) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, anime_id, comment, date);
    }


}
