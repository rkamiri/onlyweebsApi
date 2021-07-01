package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.IsListedInEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeStatsDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AverageStatsDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.GeneralStatsDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final ListsRepository listsRepository;
    private final IsListedInRepository listedInRepository;
    private final AnimeRepository animeRepository;
    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;
    private final RatingRepository ratingRepository;

    public StatsService(ListsRepository listsRepository, IsListedInRepository listedInRepository, AnimeRepository animeRepository, CommentRepository commentRepository, UsersRepository usersRepository, RatingRepository ratingRepository) {
        this.listsRepository = listsRepository;
        this.listedInRepository = listedInRepository;
        this.animeRepository = animeRepository;
        this.commentRepository = commentRepository;
        this.usersRepository = usersRepository;
        this.ratingRepository = ratingRepository;
    }

    public GeneralStatsDto getGeneralStats() {
        return new GeneralStatsDto(
                this.getNumberOfAnimes(),
                this.getNumberOfComments(),
                this.getNumberOfLists(),
                this.getNumberOfUsers()
        );
    }

    public AverageStatsDto getAverageStatsByUser() {
        return this.averageStatsByUser();
    }

    public List<AnimeStatsDto> getAnimesAndListedNum() {
        return this.getAnimesAndNumberOfTimesItWasListed();
    }

    public long getNumberOfAnimes() {
        return this.animeRepository.count();
    }

    public long getNumberOfComments() {
        return this.commentRepository.count();
    }

    public long getNumberOfLists() {
        return this.listsRepository.count();
    }

    public long getNumberOfUsers() {
        return this.usersRepository.count();
    }

    public long getNumberOfRatings() {
        return this.ratingRepository.count();
    }

    public List<AnimeEntity> findAnimeEntityListOfList(ListsEntity listsEntity) {
        List<AnimeEntity> animeEntities = new ArrayList<>();
        List<IsListedInEntity> x = this.listedInRepository.findAll();
        for (IsListedInEntity s : x) {
            if (s.getListId().equals(listsEntity.getId())) {
                animeEntities.add(this.animeRepository.getOne(s.getAnimeId()));
            }
        }
        return animeEntities;
    }

    public long getNumberOfAnimesInDefaultList(String whichDefaultList) {
        long numberOfAnimesInDefaultList = 0;
        List<ListsEntity> defaultLists = this.listsRepository.findByName(whichDefaultList);
        for (ListsEntity listsEntity : defaultLists) {
            numberOfAnimesInDefaultList += this.findAnimeEntityListOfList(listsEntity).size();
        }
        return numberOfAnimesInDefaultList;
    }

    public AverageStatsDto averageStatsByUser() {
        AverageStatsDto averageStatsDto = new AverageStatsDto();
        long nbUsers = this.getNumberOfUsers();
        if (nbUsers > 0) {
            averageStatsDto.setCommentsByUser((double) this.getNumberOfComments() / (double) nbUsers);
            averageStatsDto.setListsByUser((double) this.getNumberOfLists() / (double) nbUsers);
            averageStatsDto.setRatingByUser((double) this.getNumberOfRatings() / (double) nbUsers);
            averageStatsDto.setWatchedByUser((double) this.getNumberOfAnimesInDefaultList("Watched") / (double) nbUsers);
            averageStatsDto.setWatchingByUser((double) this.getNumberOfAnimesInDefaultList("Currently watching") / (double) nbUsers);
            averageStatsDto.setPlanToWatchByUser((double) this.getNumberOfAnimesInDefaultList("Plan to watch") / (double) nbUsers);
            return averageStatsDto;
        } else return new AverageStatsDto(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public List<AnimeStatsDto> getAnimesAndNumberOfTimesItWasListed() {
        List<AnimeStatsDto> animeStatsDtoList = new ArrayList<>();
        List<IsListedInEntity> isListedInEntityList = this.listedInRepository.findAll();
        List<Long> animeIds = new ArrayList<>(new HashSet<>(isListedInEntityList.stream().map(isListedInEntity -> isListedInEntity.getAnimeId()).collect(Collectors.toList())));
        for (Long animeId : animeIds) {
            animeStatsDtoList.add(new AnimeStatsDto(new AnimeDto(this.animeRepository.getOne(animeId)), this.listedInRepository.countByAnimeId(animeId)));
        }
        Collections.sort(animeStatsDtoList, (anime1, anime2) -> (int) (anime2.getNumberOfTimesListed() - anime1.getNumberOfTimesListed()));
        return animeStatsDtoList;
    }
}
