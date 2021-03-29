package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class IsListedIn {

    private Long id;
    private Long list_id;
    private Long anime_id;

    public IsListedIn(Long id, Long list_id, Long anime_id) {
        this.id = id;
        this.list_id = list_id;
        this.anime_id = anime_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getList_id() {
        return list_id;
    }

    public void setList_id(Long list_id) {
        this.list_id = list_id;
    }

    public Long getAnime_id() {
        return anime_id;
    }

    public void setAnime_id(Long anime_id) {
        this.anime_id = anime_id;
    }
}
