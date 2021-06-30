package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.RatingEntity;
import lombok.Data;

@Data
public class Rating {

    private Long userId;
    private Long animeId;
    private Long rate;

    public Rating() {
    }

    public Rating(RatingEntity re) {
        this.userId = re.getUserId();
        this.animeId = re.getAnimeId();
        this.rate = re.getRate();
    }
}
