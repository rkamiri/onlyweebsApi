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
    private Long animeId;

    public IsListedInEntity() {}

    public IsListedInEntity(Long listId, Long animeId) {
        this.listId = listId;
        this.animeId = animeId;
    }

    public Long getId() {
        return id;
    }

    public Long getListId() {
        return listId;
    }

    public Long getAnimeId() {
        return animeId;
    }

    public void setListId(Long list_id) {
        this.listId = list_id;
    }

    public void setAnimeId(Long animeId) {
        this.animeId = animeId;
    }
}
