package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeStats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.GeneralStats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.StatsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    private final StatsRepository statsRepository;

    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public GeneralStats getGeneralStats() {
        return new GeneralStats(
                this.statsRepository.getNumberOfAnimes(),
                this.statsRepository.getNumberOfComments(),
                this.statsRepository.getNumberOfLists(),
                this.statsRepository.getNumberOfUsers()
        );
    }

    public double getNumberOfCommentsByUser() {
        return this.statsRepository.numberOfCommentsByUser();
    }

    public List<AnimeStats> getAnimesAndListedNum() {
        return this.statsRepository.getAnimesAndNumberOfTimesItWasListed();
    }
}
