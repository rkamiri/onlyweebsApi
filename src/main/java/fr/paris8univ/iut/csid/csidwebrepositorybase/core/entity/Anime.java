package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

public class Anime {
    private Long id;
    private String title;
    private String internationalTitle;
    private String synopsis;
    private String cover;
    private int ranking;

    public Anime(AnimeEntity animeEntity) {
        this.id=animeEntity.getId();
        this.title = animeEntity.getTitle();
        this.internationalTitle = animeEntity.getInternational_title();
        this.synopsis = animeEntity.getSynopsis();
        this.cover = animeEntity.getCover();
        this.ranking = animeEntity.getRanking();
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

    public void setInternationalTitle(String internationalTitle) {
        this.internationalTitle = internationalTitle;
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
