package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class AnimeStats {
    private Anime anime;
    private long numberOfTimesListed;

    public AnimeStats(Anime anime, long numberOfTimesListed) {
        this.anime = anime;
        this.numberOfTimesListed = numberOfTimesListed;
    }

    public Anime getAnime() {
        return anime;
    }

    public long getNumberOfTimesListed() {
        return numberOfTimesListed;
    }
}
