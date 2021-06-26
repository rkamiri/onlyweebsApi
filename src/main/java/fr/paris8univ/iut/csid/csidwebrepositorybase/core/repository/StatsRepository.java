package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.IsListedInEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StatsRepository {

    private final ListsDao listsDao;
    private final IsListedInDao listedInDao;
    private final AnimeDao animeDao;
    private final CommentDao commentDao;
    private final UsersDao usersDao;

    @Autowired
    public StatsRepository(ListsDao listsDao, IsListedInDao listedInDao, AnimeDao animeDao, CommentDao commentDao, UsersDao usersDao) {
        this.listsDao = listsDao;
        this.listedInDao = listedInDao;
        this.animeDao = animeDao;
        this.commentDao = commentDao;
        this.usersDao = usersDao;
    }

    public long getNumberOfAnimes() {
        return this.animeDao.count();
    }

    public long getNumberOfComments() {
        return this.commentDao.count();
    }

    public long getNumberOfLists() {
        return this.listsDao.count();
    }

    public long getNumberOfUsers() {
        return this.usersDao.count();
    }

    public double numberOfCommentsByUser() {
        NumberFormat format = new DecimalFormat("#.###");
        long nbUsers = this.getNumberOfUsers();
        if (nbUsers > 0)
            return Double.parseDouble(format.format((double) this.getNumberOfComments() / (double) nbUsers));
        else
            return 0.0;
    }

    public List<AnimeStats> getAnimesAndNumberOfTimesItWasListed() {
        List<AnimeStats> animeStatsList = new ArrayList<>();
        List<IsListedInEntity> isListedInEntityList = this.listedInDao.findAll();
        List<Long> animeIds = new ArrayList<>(new HashSet<>(isListedInEntityList.stream().map(isListedInEntity -> isListedInEntity.getAnimeId()).collect(Collectors.toList())));
        for (Long animeId : animeIds) {
            animeStatsList.add(new AnimeStats(new Anime(this.animeDao.getOne(animeId)), this.listedInDao.countByAnimeId(animeId)));
        }
        Collections.sort(animeStatsList, (anime1, anime2) -> (int) (anime2.getNumberOfTimesListed() - anime1.getNumberOfTimesListed()));
        return animeStatsList;
    }
}
