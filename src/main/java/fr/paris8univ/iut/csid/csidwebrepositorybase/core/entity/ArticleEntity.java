package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "created_at")
    private String created_at;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UsersEntity author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_id", referencedColumnName = "id")
    private ImageEntity cover;

    public ArticleEntity(String title, String body, String created_at, UsersEntity author, ImageEntity cover) {
        this.title = title;
        this.body = body;
        this.created_at = created_at;
        this.author = author;
        this.cover = cover;
    }

    public ArticleEntity() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public UsersEntity getAuthor() {
        return author;
    }

    public void setAuthor(UsersEntity author) {
        this.author = author;
    }

    public ImageEntity getCover() {
        return cover;
    }

    public void setCover(ImageEntity cover) {
        this.cover = cover;
    }
}
