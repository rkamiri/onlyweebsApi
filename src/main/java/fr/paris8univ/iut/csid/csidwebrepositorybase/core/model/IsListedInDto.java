package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import lombok.Data;

@Data
public class IsListedInDto {

    private Long id;
    private Long list_id;
    private Long anime_id;

    public IsListedInDto(Long id, Long list_id, Long anime_id) {
        this.id = id;
        this.list_id = list_id;
        this.anime_id = anime_id;
    }
}
