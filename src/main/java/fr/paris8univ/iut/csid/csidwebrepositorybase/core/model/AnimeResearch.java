package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class AnimeResearch {
    private Long producer;
    private Long studio;
    private Long genre;

    public AnimeResearch(Long producer, Long studio, Long genre) {
        this.producer = producer;
        this.studio = studio;
        this.genre = genre;
    }

    public Long getProducer() {
        return producer;
    }

    public void setProducer(Long producer) {
        this.producer = producer;
    }

    public Long getStudio() {
        return studio;
    }

    public void setStudio(Long studio) {
        this.studio = studio;
    }

    public Long getGenre() {
        return genre;
    }

    public void setGenre(Long genre) {
        this.genre = genre;
    }
}
