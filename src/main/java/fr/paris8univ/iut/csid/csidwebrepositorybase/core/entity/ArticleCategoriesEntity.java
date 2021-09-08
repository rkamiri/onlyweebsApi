package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleCategoriesEntity that = (ArticleCategoriesEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
