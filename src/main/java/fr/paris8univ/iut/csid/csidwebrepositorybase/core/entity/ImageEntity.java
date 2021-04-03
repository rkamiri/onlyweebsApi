package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "imagesuploaded")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Lob
    @Column(name = "content")
    byte[] content;

    @Column(name = "name")
    String name;

    public ImageEntity(byte[] content, String name) {
        this.content = content;
        this.name = name;
    }

    public ImageEntity() {}

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
