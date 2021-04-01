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

    public List<AnimeCommentEntity> getComments() {
        return this.animeCommentRepository.getAnimeCommentDao().findAll();
    }

    public void putAComment(AnimeComment comment)  {
        this.animeCommentRepository.putAComment(comment);
    }

    public List<AnimeCommentEntity> getAnimeComments(Long animeid) {
        return this.animeCommentRepository.getAllCommentsByAnimeId(animeid);
    }

    public String getCurrentUserCommentForASelectAnime(String currentUserLogin, Long animeid) {
        if (this.animeCommentRepository.getCurrentUserAnimeCommentForASelectAnime(currentUserLogin, animeid).equals(Optional.empty())) {
            return "666";
        } else {
            return this.animeCommentRepository.getCurrentUserAnimeCommentForASelectAnime(currentUserLogin, animeid).get().getComment();
        }
    }
}
