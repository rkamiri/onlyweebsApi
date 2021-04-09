package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeCommentDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeComment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeCommentId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimeCommentRepository {

    private final AnimeCommentDao animeCommentDao;
    public final UsersRepository ur;

    @Autowired
    public AnimeCommentRepository(AnimeCommentDao animeCommentDao, UsersRepository ur) {
        this.animeCommentDao = animeCommentDao;
        this.ur = ur;
    }

    public void putAComment(AnimeComment comment)  {
        this.animeCommentDao.save(new AnimeCommentEntity(comment.getUserId(), comment.getAnimeId(), comment.getComment()));
    }

    public List<AnimeCommentEntity> getAllCommentsByAnimeId(long animeid) {
        List<AnimeCommentEntity> comments = this.animeCommentDao.findAll();
        comments.removeIf(entity -> !entity.getAnime_id().equals(animeid));
        return comments;
    }

    public AnimeCommentDao getAnimeCommentDao() {
        return animeCommentDao;
    }

    public Optional<AnimeComment> getCurrentUserAnimeCommentForASelectAnime(String currentUserLogin, Long animeid) {
        if (ur.findByUsername(currentUserLogin).isPresent()) {
            return this.animeCommentDao.findById(new AnimeCommentId(ur.findByUsername(currentUserLogin).get().getId(), animeid)).map(AnimeComment::new);
        } else {
            return Optional.empty();
        }
    }
}
