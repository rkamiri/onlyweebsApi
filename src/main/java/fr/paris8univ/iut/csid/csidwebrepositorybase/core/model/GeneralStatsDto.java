package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import lombok.Data;

@Data
public class GeneralStatsDto {
    private long animes;
    private long comments;
    private long lists;
    private long users;

    public GeneralStatsDto(long animes, long comments, long lists, long users) {
        this.animes = animes;
        this.comments = comments;
        this.lists = lists;
        this.users = users;
    }
}
