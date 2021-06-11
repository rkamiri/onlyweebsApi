package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "article_categories")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArticleCategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bg_color")
    private String bgColor;

    public ArticleCategoriesEntity(Long id, String name, String bgColor) {
        this.id = id;
        this.name = name;
        this.bgColor = bgColor;
    }

    public ArticleCategoriesEntity(Long id) {
        this.id = id;
    }

    public ArticleCategoriesEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
