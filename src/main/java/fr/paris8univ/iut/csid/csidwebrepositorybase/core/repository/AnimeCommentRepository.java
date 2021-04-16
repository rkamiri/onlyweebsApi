package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeCommentDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeComment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeCommentId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimeCommentRepository {

    private final AnimeCommentDao animeCommentDao;
    public final UsersRepository ur;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public AnimeCommentRepository(AnimeCommentDao animeCommentDao, UsersRepository ur) {
        this.animeCommentDao = animeCommentDao;
        this.ur = ur;
    }

    public void putAComment(AnimeComment comment)  {
        List<AnimeCommentEntity> animeCommentEntities = this.animeCommentDao.findAll();
        animeCommentEntities.removeIf(x -> !x.getUser_id().equals(comment.getUser_id()));
        animeCommentEntities.removeIf(x -> !x.getAnime_id().equals(comment.getAnime_id()));
        LocalDateTime now = LocalDateTime.now();
        if(animeCommentEntities.size()>0){
            animeCommentEntities.get(0).setComment(comment.getComment());
            animeCommentEntities.get(0).setDate(dtf.format(now));
            animeCommentDao.save(animeCommentEntities.get(0));
        }
        else{
            this.animeCommentDao.save(new AnimeCommentEntity(comment.getUser_id(), comment.getAnime_id(), comment.getComment(),dtf.format(now)));
        }
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
