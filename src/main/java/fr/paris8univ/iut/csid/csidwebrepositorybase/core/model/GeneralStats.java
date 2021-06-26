package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class GeneralStats {
    private long animes;
    private long comments;
    private long lists;
    private long users;

    public GeneralStats(long animes, long comments, long lists, long users) {
        this.animes = animes;
        this.comments = comments;
        this.lists = lists;
        this.users = users;
    }

    public long getAnimes() {
        return animes;
    }

    public long getComments() {
        return comments;
    }

    public long getLists() {
        return lists;
    }

    public long getUsers() {
        return users;
    }
}
