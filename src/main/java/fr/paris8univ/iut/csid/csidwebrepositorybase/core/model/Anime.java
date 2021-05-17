package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;

import javax.persistence.Column;

public class Anime {
    private Long id;
    private String title;
    private String synopsis;
    private String cover;
    private String genre;
    private String aired;
    private Long ranking;
    private Long episodes;

    public Anime(AnimeEntity animeEntity) {
        id = animeEntity.getId();
        title = animeEntity.getTitle();
        synopsis = animeEntity.getSynopsis();
        cover = animeEntity.getCover();
        genre = animeEntity.getGenre();
        aired = animeEntity.getAired();
        ranking = animeEntity.getRanking();
        episodes = animeEntity.getEpisodes();
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }

    public Long getRanking() {
        return ranking;
    }

    public void setRanking(Long ranking) {
        this.ranking = ranking;
    }

    public Long getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Long episodes) {
        this.episodes = episodes;
    }
}
