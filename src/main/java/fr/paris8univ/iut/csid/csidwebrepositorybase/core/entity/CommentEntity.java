package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties("animeEntity")
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;

    @Column(name = "body")
    private String body;

    @Column(name = "post_date")
    private String date;

    @ManyToOne
    @JoinColumn(name="anime_id")
    private AnimeEntity animeEntity;

    public CommentEntity() { }

    public CommentEntity(UsersEntity usersEntity, String body, String date, AnimeEntity animeEntity) {
        this.usersEntity = usersEntity;
        this.body = body;
        this.date = date;
        this.animeEntity = animeEntity;
    }

    public Long getId() {
        return id;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date;
    }

    public AnimeEntity getAnimeEntity() {
        return animeEntity;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
