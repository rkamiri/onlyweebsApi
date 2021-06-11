package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"animeEntity", "articleEntity"})
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

    @ManyToOne
    @JoinColumn(name="article_id")
    private ArticleEntity articleEntity;

    @ManyToOne
    @JoinColumn(name="list_id")
    private ListsEntity listsEntity;

    public CommentEntity() { }

    public CommentEntity(UsersEntity usersEntity, String body, String date) {
        this.usersEntity = usersEntity;
        this.body = body;
        this.date = date;
    }

    public ArticleEntity getArticleEntity() {
        return articleEntity;
    }

    public void setAnimeEntity(AnimeEntity animeEntity) {
        this.animeEntity = animeEntity;
    }

    public void setArticleEntity(ArticleEntity articleEntity) {
        this.articleEntity = articleEntity;
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

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    public ListsEntity getListsEntity() {
        return listsEntity;
    }

    public void setListsEntity(ListsEntity listsEntity) {
        this.listsEntity = listsEntity;
    }
}
