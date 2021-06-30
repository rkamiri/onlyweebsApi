package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.GenreEntity;
import lombok.Data;

@Data
public class GenreDto {
    private Long id;
    private String name;

    public GenreDto(GenreEntity genreEntity) {
        this.id = genreEntity.getId();
        this.name = genreEntity.getName();
    }
}
