package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
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
}
