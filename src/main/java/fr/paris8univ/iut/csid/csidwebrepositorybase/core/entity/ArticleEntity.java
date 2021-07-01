package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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
    private UserEntity author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_id", referencedColumnName = "id")
    private ImageEntity cover;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ArticleCategoriesEntity category;

    public ArticleEntity(String title, String body, String created_at, UserEntity author, ImageEntity cover, ArticleCategoriesEntity category) {
        this.title = title;
        this.body = body;
        this.created_at = created_at;
        this.author = author;
        this.cover = cover;
        this.category = category;
    }

    public ArticleEntity() {
    }
}
