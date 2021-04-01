package fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "islistedin")
public class IsListedInEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "list_id", nullable = false)
    private Long list_id;

    @Column(name = "anime_id", nullable = false)
    private Long anime_id;

    public IsListedInEntity() {}

    public IsListedInEntity(Long listId, Long animeId) {
        this.list_id = listId;
        this.anime_id = animeId;
    }

    public Long getId() {
        return id;
    }

    public Long getList_id() {
        return list_id;
    }

    public Long getAnime_id() {
        return anime_id;
    }

    public void setList_id(Long list_id) {
        this.list_id = list_id;
    }

    public void setAnime_id(Long anime_id) {
        this.anime_id = anime_id;
    }
}
