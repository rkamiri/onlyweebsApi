package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.PegiEntity;
import lombok.Data;

@Data
public class AnimeDto {
    private Long id;
    private String title;
    private String titleEnglish;
    private String synopsis;
    private String imgUrl;
    private Long episodes;
    private String airing;
    private boolean aired;
    private PegiEntity pegi;

    public AnimeDto(AnimeEntity animeEntity) {
        this.id = animeEntity.getId();
        this.title = animeEntity.getTitle();
        this.titleEnglish = animeEntity.getTitleEnglish();
        this.synopsis = animeEntity.getSynopsis();
        this.imgUrl = animeEntity.getImgUrl();
        this.episodes = animeEntity.getEpisodes();
        this.airing = animeEntity.getAiring();
        this.aired = animeEntity.isAired();
        this.pegi = animeEntity.getPegiEntity();
    }
}
