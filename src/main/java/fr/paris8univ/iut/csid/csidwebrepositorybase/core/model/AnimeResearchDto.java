package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import lombok.Data;

@Data
public class AnimeResearchDto {
    private Long producer;
    private Long studio;
    private Long genre;

    public AnimeResearchDto(Long producer, Long studio, Long genre) {
        this.producer = producer;
        this.studio = studio;
        this.genre = genre;
    }
}
