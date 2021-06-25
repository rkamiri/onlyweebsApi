package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class AnimeStats {
    private long animeId;
    private long numberOfTimesListed;

    public AnimeStats(long animeId, long numberOfTimesListed) {
        this.animeId = animeId;
        this.numberOfTimesListed = numberOfTimesListed;
    }

    public long getAnimeId() {
        return animeId;
    }

    public long getNumberOfTimesListed() {
        return numberOfTimesListed;
    }
}
