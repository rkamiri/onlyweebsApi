package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

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
@Table(name = "anime")
public class AnimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "title_english")
    private String titleEnglish;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "episodes")
    private Long episodes;

    @Column(name = "airing")
    private String airing;

    @Column(name = "aired")
    private boolean aired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pegi_id")
    @ToString.Exclude
    private PegiEntity pegiEntity;

    public AnimeEntity() {
    }

    public AnimeEntity(String title, String titleEnglish, String synopsis, String imgUrl, Long episodes, String airing, boolean aired, PegiEntity pegiEntity) {
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.synopsis = synopsis;
        this.imgUrl = imgUrl;
        this.episodes = episodes;
        this.airing = airing;
        this.aired = aired;
        this.pegiEntity = pegiEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AnimeEntity that = (AnimeEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
