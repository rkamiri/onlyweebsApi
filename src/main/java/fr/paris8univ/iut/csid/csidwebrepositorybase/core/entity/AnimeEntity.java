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

    @Column(name = "international_title")
    private String international_title;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "cover")
    private String cover;

    @Column(name = "ranking")
    private int ranking;

    public AnimeEntity(){}

    public AnimeEntity(String title, String international_title, String synopsis, String cover, int ranking) {
        this.title = title;
        this.international_title = international_title;
        this.synopsis = synopsis;
        this.cover = cover;
        this.ranking = ranking;
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

    public String getInternational_title() {
        return international_title;
    }

    public void setInternational_title(String international_title) {
        this.international_title = international_title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
