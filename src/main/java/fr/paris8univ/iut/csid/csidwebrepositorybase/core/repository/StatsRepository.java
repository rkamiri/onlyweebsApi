package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.IsListedInEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Anime;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        return this.getNumberOfComments() / this.getNumberOfUsers();
    }

    public List<AnimeStats> getAnimesAndNumberOfTimesItWasListed() {
        List<AnimeStats> animeStatsList = new ArrayList<>();
        for (AnimeEntity animeEntity : this.animeDao.findAll()) {
            if (this.listedInDao.findTopByAnimeId(animeEntity.getId()).isPresent()) {
                animeStatsList.add(new AnimeStats(new Anime(animeEntity), this.listedInDao.countByAnimeId(animeEntity.getId())));
            }
        }
        Collections.sort(animeStatsList, (anime1, anime2) -> {
            return (int) (anime2.getNumberOfTimesListed() - anime1.getNumberOfTimesListed());
        });
        return animeStatsList;
    }
}
