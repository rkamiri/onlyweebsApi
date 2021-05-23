package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "anime")
public class AnimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "title_english")
    private String titleEnglish;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "episodes")
    private Long episodes;

    @Column(name = "airing")
    private String airing;

    @Column(name = "aired")
    private boolean aired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pegi_id")
    private PegiEntity pegiEntity;

    @OneToMany(mappedBy="animeEntity")
    private Set<CommentEntity> commentEntity;

    public AnimeEntity() {
    }

    public AnimeEntity(String title, String titleEnglish, String synopsis, String imgUrl, Long episodes, String airing, boolean aired, PegiEntity pegiEntity) {
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.synopsis = synopsis;
        this.imgUrl = imgUrl;
        this.episodes = episodes;
        this.airing = airing;
        this.aired = aired;
        this.pegiEntity = pegiEntity;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Long getEpisodes() {
        return episodes;
    }

    public String getAiring() {
        return airing;
    }

    public boolean isAired() {
        return aired;
    }

    public PegiEntity getPegiEntity() {
        return pegiEntity;
    }

    public Set<CommentEntity> getCommentEntity() {
        return commentEntity;
    }
}
