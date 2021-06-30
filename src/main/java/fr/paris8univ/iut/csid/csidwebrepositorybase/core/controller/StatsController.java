package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeStats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AverageStats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.GeneralStats;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.StatsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/general")
    public ResponseEntity<GeneralStats> getGeneralStats() {
        GeneralStats stats = this.statsService.getGeneralStats();
        MediaType contentType = MediaType.valueOf("application/json");
        if (UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }")) {
            return ResponseEntity.status(200).contentType(contentType).body(stats);
        }
        return ResponseEntity.status(401).contentType(contentType).body(stats);

    }

    @GetMapping("/average")
    public ResponseEntity<AverageStats> getNumberOfCommentsByUser() {
        MediaType contentType = MediaType.valueOf("application/json");
        AverageStats commentsByUser = new AverageStats();
        if (UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }")) {
            commentsByUser = this.statsService.getAverageStatsByUser();
            return ResponseEntity.status(200).contentType(contentType).body(commentsByUser);
        }
        return ResponseEntity.status(401).contentType(contentType).body(commentsByUser);
    }

    @GetMapping("/animes-listed")
    public ResponseEntity<List<AnimeStats>> getAnimesAndListedNum() {
        List<AnimeStats> animeStatsList = this.statsService.getAnimesAndListedNum();
        MediaType contentType = MediaType.valueOf("application/json");
        if (UserController.getCurrentUserRole().equals("{ \"auth\": \"ROLE_ADMIN\" }")) {
            return ResponseEntity.status(200).contentType(contentType).body(animeStatsList);
        }
        return ResponseEntity.status(401).contentType(contentType).body(animeStatsList);
    }
}
