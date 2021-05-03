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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;

    @Column(name = "anime_id", nullable = false)
    private Long anime_id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "post_date")
    private String date;

    public AnimeCommentEntity() {
    }

    public AnimeCommentEntity(UsersEntity ue, Long anime_id, String comment, String date) {
        this.usersEntity = ue;
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

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimeCommentEntity that = (AnimeCommentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(usersEntity, that.usersEntity) && Objects.equals(anime_id, that.anime_id) && Objects.equals(comment, that.comment) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usersEntity, anime_id, comment, date);
    }

    @Override
    public String toString() {
        return "AnimeCommentEntity{" +
                "id=" + id +
                ", usersEntity=" + usersEntity +
                ", anime_id=" + anime_id +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
