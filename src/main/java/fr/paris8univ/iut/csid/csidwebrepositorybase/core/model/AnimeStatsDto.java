package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import lombok.Data;

@Data
public class AnimeStatsDto {
    private AnimeDto animeDTO;
    private long numberOfTimesListed;

    public AnimeStatsDto(AnimeDto animeDTO, long numberOfTimesListed) {
        this.animeDTO = animeDTO;
        this.numberOfTimesListed = numberOfTimesListed;
    }
}
