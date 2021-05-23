package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;

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

    public AnimeEntity(Long id, String title, String titleEnglish, String synopsis, String imgUrl, Long episodes, String airing, boolean aired, PegiEntity pegiEntity) {
        this.id = id;
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.synopsis = synopsis;
        this.imgUrl = imgUrl;
        this.episodes = episodes;
        this.airing = airing;
        this.aired = aired;
        this.pegiEntity = pegiEntity;
    }

    public AnimeEntity() {

    }

    public PegiEntity getPegiEntity() {
        return pegiEntity;
    }

    public void setPegiEntity(PegiEntity pegiEntity) {
        this.pegiEntity = pegiEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String img_url) {
        this.imgUrl = img_url;
    }

    public Long getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Long episodes) {
        this.episodes = episodes;
    }

    public String getAiring() {
        return airing;
    }

    public void setAiring(String airing) {
        this.airing = airing;
    }

    public boolean isAired() {
        return aired;
    }

    public void setAired(boolean aired) {
        this.aired = aired;
    }
}
