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
    private String internationalTitle;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "cover")
    private String cover;

    @Column(name = "ranking")
    private int ranking;

    public AnimeEntity(){}

    public AnimeEntity(String title, String international_title, String synopsis, String cover, int ranking) {
        this.title = title;
        this.internationalTitle = international_title;
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

    public String getInternationalTitle() {
        return internationalTitle;
    }

    public void setInternationalTitle(String international_title) {
        this.internationalTitle = international_title;
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
