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
    private Long listId;

    @Column(name = "anime_id", nullable = false)
    private Long anime_id;

    public IsListedInEntity() {}

    public IsListedInEntity(Long listId, Long animeId) {
        this.listId = listId;
        this.anime_id = animeId;
    }

    public Long getId() {
        return id;
    }

    public Long getListId() {
        return listId;
    }

    public Long getAnime_id() {
        return anime_id;
    }

    public void setListId(Long list_id) {
        this.listId = list_id;
    }

    public void setAnime_id(Long anime_id) {
        this.anime_id = anime_id;
    }
}
