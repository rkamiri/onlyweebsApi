package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;


import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;

public class Image {
    private Long id;
    private byte[] content;
    private String name;

    public Image(ImageEntity ie) {
        this.id = ie.getId();
        this.content = ie.getContent();
        this.name = ie.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
