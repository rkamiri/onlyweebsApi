package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class AnimeResearch {
    private String producer;
    private String studio;
    private String genre;

    public AnimeResearch(String producer, String studio, String genre) {
        this.producer = producer;
        this.studio = studio;
        this.genre = genre;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
