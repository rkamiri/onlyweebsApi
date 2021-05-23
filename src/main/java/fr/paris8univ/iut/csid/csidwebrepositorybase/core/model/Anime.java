package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;

import javax.persistence.Column;
import java.util.Set;

public class Anime {
    private Long id;
    private String title;
    private String titleEnglish;
    private String synopsis;
    private String imgUrl;
    private Long episodes;
    private String airing;
    private boolean aired;
    private PegiEntity pegi;
    private Set<CommentEntity> commentEntity;

    public Anime(AnimeEntity animeEntity) {
        this.id = animeEntity.getId();
        this.title = animeEntity.getTitle();
        this.titleEnglish = animeEntity.getTitleEnglish();
        this.synopsis = animeEntity.getSynopsis();
        this.imgUrl = animeEntity.getImgUrl();
        this.episodes = animeEntity.getEpisodes();
        this.airing = animeEntity.getAiring();
        this.aired = animeEntity.isAired();
        this.pegi = animeEntity.getPegiEntity();
        this.commentEntity = animeEntity.getCommentEntity();
    }

    public PegiEntity getPegi() {
        return pegi;
    }

    public void setPegi(PegiEntity pegi) {
        this.pegi = pegi;
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

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Set<CommentEntity> getCommentEntity() {
        return commentEntity;
    }

    public void setCommentEntity(Set<CommentEntity> commentEntity) {
        this.commentEntity = commentEntity;
    }
}
