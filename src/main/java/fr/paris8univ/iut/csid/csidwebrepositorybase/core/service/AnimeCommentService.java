package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeComment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.AnimeCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimeCommentService {

    private final AnimeCommentRepository animeCommentRepository;

    public AnimeCommentService(AnimeCommentRepository animeCommentRepository) {
        this.animeCommentRepository = animeCommentRepository;
    }

    public void putAComment(AnimeComment comment)  {
        this.animeCommentRepository.putAComment(comment);
    }

    public List<AnimeCommentEntity> getAnimeComments(long animeId) {
        return this.animeCommentRepository.getAllCommentsByAnimeId(animeId);
    }

    public String getCurrentUserCommentForASelectAnime(String currentUserLogin, long animeId) {
        if (this.animeCommentRepository.getCurrentUserAnimeCommentForASelectAnime(currentUserLogin, animeId).equals(Optional.empty())) {
            return "666";
        } else {
            return this.animeCommentRepository.getCurrentUserAnimeCommentForASelectAnime(currentUserLogin, animeId).orElseThrow().getComment();
        }
    }

    public void deleteComment(String currentUserLogin, long animeId) {
        this.animeCommentRepository.deleteComment(currentUserLogin, animeId);
    }
}
