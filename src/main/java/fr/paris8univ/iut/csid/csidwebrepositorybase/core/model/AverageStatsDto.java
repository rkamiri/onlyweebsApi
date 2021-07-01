package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import lombok.Data;

@Data
public class AverageStatsDto {
    private double commentsByUser;
    private double listsByUser;
    private double ratingByUser;
    private double watchedByUser;
    private double watchingByUser;
    private double planToWatchByUser;

    public AverageStatsDto() {
    }

    public AverageStatsDto(double commentsByUser, double listsByUser, double ratingByUser, double watchedByUser, double watchingByUser, double planToWatchByUser) {
        this.commentsByUser = commentsByUser;
        this.listsByUser = listsByUser;
        this.ratingByUser = ratingByUser;
        this.watchedByUser = watchedByUser;
        this.watchingByUser = watchingByUser;
        this.planToWatchByUser = planToWatchByUser;
    }
}
