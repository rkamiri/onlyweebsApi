package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class AnimeStats {
    private AnimeDto animeDTO;
    private long numberOfTimesListed;

    public AnimeStats(AnimeDto animeDTO, long numberOfTimesListed) {
        this.animeDTO = animeDTO;
        this.numberOfTimesListed = numberOfTimesListed;
    }

    public AnimeDto getAnime() {
        return animeDTO;
    }

    public long getNumberOfTimesListed() {
        return numberOfTimesListed;
    }
}
