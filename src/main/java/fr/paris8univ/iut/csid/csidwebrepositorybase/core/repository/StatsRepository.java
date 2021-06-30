package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.IsListedInEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeStats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AverageStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StatsRepository {

    private final ListsDao listsDao;
    private final IsListedInDao listedInDao;
    private final AnimeRepository animeRepository;
    private final CommentRepository commentRepository;
    private final UsersDao usersDao;
    private final RatingRepository ratingRepository;
    private final NumberFormat format = new DecimalFormat("#.###");

    @Autowired
    public StatsRepository(ListsDao listsDao, IsListedInDao listedInDao, AnimeRepository animeRepository, CommentRepository commentRepository, UsersDao usersDao, RatingRepository ratingRepository) {
        this.listsDao = listsDao;
        this.listedInDao = listedInDao;
        this.animeRepository = animeRepository;
        this.commentRepository = commentRepository;
        this.usersDao = usersDao;
        this.ratingRepository = ratingRepository;
    }

    public long getNumberOfAnimes() {
        return this.animeRepository.count();
    }

    public long getNumberOfComments() {
        return this.commentRepository.count();
    }

    public long getNumberOfLists() {
        return this.listsDao.count();
    }

    public long getNumberOfUsers() {
        return this.usersDao.count();
    }

    public long getNumberOfRatings() {
        return this.ratingRepository.count();
    }

    public List<AnimeEntity> findAnimeEntityListOfList(ListsEntity listsEntity) {
        List<AnimeEntity> animeEntities = new ArrayList<>();
        List<IsListedInEntity> x = this.listedInDao.findAll();
        for (IsListedInEntity s : x) {
            if (s.getListId().equals(listsEntity.getId())) {
                animeEntities.add(this.animeRepository.getOne(s.getAnimeId()));
            }
        }
        return animeEntities;
    }

    public long getNumberOfAnimesInDefaultList(String whichDefaultList) {
        long numberOfAnimesInDefaultList = 0;
        List<ListsEntity> defaultLists = this.listsDao.findByName(whichDefaultList);
        for (ListsEntity listsEntity : defaultLists) {
            numberOfAnimesInDefaultList += this.findAnimeEntityListOfList(listsEntity).size();
        }
        return numberOfAnimesInDefaultList;
    }

    public AverageStats averageStatsByUser() {
        AverageStats averageStats = new AverageStats();
        long nbUsers = this.getNumberOfUsers();
        if (nbUsers > 0) {
            averageStats.setCommentsByUser((double) this.getNumberOfComments() / (double) nbUsers);
            averageStats.setListsByUser((double) this.getNumberOfLists() / (double) nbUsers);
            averageStats.setRatingByUser((double) this.getNumberOfRatings() / (double) nbUsers);
            averageStats.setWatchedByUser((double) this.getNumberOfAnimesInDefaultList("Watched") / (double) nbUsers);
            averageStats.setWatchingByUser((double) this.getNumberOfAnimesInDefaultList("Currently watching") / (double) nbUsers);
            averageStats.setPlanToWatchByUser((double) this.getNumberOfAnimesInDefaultList("Plan to watch") / (double) nbUsers);
            return averageStats;
        } else return new AverageStats(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public List<AnimeStats> getAnimesAndNumberOfTimesItWasListed() {
        List<AnimeStats> animeStatsList = new ArrayList<>();
        List<IsListedInEntity> isListedInEntityList = this.listedInDao.findAll();
        List<Long> animeIds = new ArrayList<>(new HashSet<>(isListedInEntityList.stream().map(isListedInEntity -> isListedInEntity.getAnimeId()).collect(Collectors.toList())));
        for (Long animeId : animeIds) {
            animeStatsList.add(new AnimeStats(new AnimeDto(this.animeRepository.getOne(animeId)), this.listedInDao.countByAnimeId(animeId)));
        }
        Collections.sort(animeStatsList, (anime1, anime2) -> (int) (anime2.getNumberOfTimesListed() - anime1.getNumberOfTimesListed()));
        return animeStatsList;
    }
}
