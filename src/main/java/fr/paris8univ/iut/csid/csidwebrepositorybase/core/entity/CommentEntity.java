package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
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
    private UserEntity userEntity;

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

    public CommentEntity(UserEntity userEntity, String body, String date) {
        this.userEntity = userEntity;
        this.body = body;
        this.date = date;
    }
}
