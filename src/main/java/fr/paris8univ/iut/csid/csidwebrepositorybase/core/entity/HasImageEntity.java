package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "has_image")
public class HasImageEntity {

    @Id
    @Column(name = "userid", nullable = false, unique = true)
    private Long userid;

    @Column(name = "imageid", nullable = false)
    private Long imageid;

    public HasImageEntity(Long userid, Long imageid) {
        this.userid = userid;
        this.imageid = imageid;
    }

    public HasImageEntity() {}

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getImageid() {
        return imageid;
    }

    public void setImageid(Long imageid) {
        this.imageid = imageid;
    }
}
