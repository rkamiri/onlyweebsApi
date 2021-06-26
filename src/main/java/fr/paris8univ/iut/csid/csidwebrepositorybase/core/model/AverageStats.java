package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

public class AverageStats {
    private double commentsByUser;
    private double listsByUser;
    private double ratingByUser;
    private double watchedByUser;
    private double watchingByUser;
    private double planToWatchByUser;

    public AverageStats() {

    }

    public AverageStats(double commentsByUser, double listsByUser, double ratingByUser, double watchedByUser, double watchingByUser, double planToWatchByUser) {
        this.commentsByUser = commentsByUser;
        this.listsByUser = listsByUser;
        this.ratingByUser = ratingByUser;
        this.watchedByUser = watchedByUser;
        this.watchingByUser = watchingByUser;
        this.planToWatchByUser = planToWatchByUser;
    }

    public double getCommentsByUser() {
        return commentsByUser;
    }

    public double getListsByUser() {
        return listsByUser;
    }

    public double getRatingByUser() {
        return ratingByUser;
    }

    public double getWatchedByUser() {
        return watchedByUser;
    }

    public double getWatchingByUser() {
        return watchingByUser;
    }

    public double getPlanToWatchByUser() {
        return planToWatchByUser;
    }

    public void setCommentsByUser(double commentsByUser) {
        this.commentsByUser = commentsByUser;
    }

    public void setListsByUser(double listsByUser) {
        this.listsByUser = listsByUser;
    }

    public void setRatingByUser(double ratingByUser) {
        this.ratingByUser = ratingByUser;
    }

    public void setWatchedByUser(double watchedByUser) {
        this.watchedByUser = watchedByUser;
    }

    public void setWatchingByUser(double watchingByUser) {
        this.watchingByUser = watchingByUser;
    }

    public void setPlanToWatchByUser(double planToWatchByUser) {
        this.planToWatchByUser = planToWatchByUser;
    }
}
