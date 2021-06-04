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

    @OneToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UsersEntity author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_id", referencedColumnName = "id")
    private ImageEntity cover;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ArticleCategoriesEntity category;

    public ArticleEntity(String title, String body, String created_at, UsersEntity author, ImageEntity cover, ArticleCategoriesEntity category) {
        this.title = title;
        this.body = body;
        this.created_at = created_at;
        this.author = author;
        this.cover = cover;
        this.category = category;
    }

    public ArticleEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public UsersEntity getAuthor() {
        return author;
    }

    public void setAuthor(UsersEntity author) {
        this.author = author;
    }

    public ImageEntity getCover() {
        return cover;
    }

    public ArticleCategoriesEntity getCategory() {
        return category;
    }
}
