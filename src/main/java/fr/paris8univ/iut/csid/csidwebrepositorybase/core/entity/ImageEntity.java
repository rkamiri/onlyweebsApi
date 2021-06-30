package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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

    public ImageEntity(Long id, byte[] content, String name) {
        this.id = id;
        this.content = content;
        this.name = name;
    }

    public ImageEntity() {
    }
}
