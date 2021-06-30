package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;


import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import lombok.Data;

@Data
public class ImageDto {
    private Long id;
    private byte[] content;
    private String name;

    public ImageDto() {
    }

    public ImageDto(ImageEntity ie) {
        this.id = ie.getId();
        this.content = ie.getContent();
        this.name = ie.getName();
    }
}
