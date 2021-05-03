package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller.UserController;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeCommentDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
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
    public final UsersRepository usersRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public AnimeCommentRepository(AnimeCommentDao animeCommentDao, UsersRepository usersRepository) {
        this.animeCommentDao = animeCommentDao;
        this.usersRepository = usersRepository;
    }

    public void putAComment(AnimeComment comment) {
        UsersEntity user = this.usersRepository.findUserEntityByUsername(UserController.getCurrentUserLogin());
        List<AnimeCommentEntity> animeCommentEntities = this.animeCommentDao.findAll();
        animeCommentEntities.removeIf(x -> !x.getUsersEntity().getId().equals(user.getId()));
        animeCommentEntities.removeIf(x -> !x.getAnime_id().equals(comment.getAnime_id()));
        LocalDateTime now = LocalDateTime.now();
        if (animeCommentEntities.size() > 0) {
            animeCommentEntities.get(0).setComment(comment.getComment());
            animeCommentEntities.get(0).setDate(dtf.format(now));
            animeCommentDao.save(animeCommentEntities.get(0));
        } else {
            this.animeCommentDao.save(
                    new AnimeCommentEntity(
                            user,
                            comment.getAnime_id(), comment.getComment(),
                            dtf.format(now)));
        }
    }

    public List<AnimeCommentEntity> getAllCommentsByAnimeId(long animeId) {
        List<AnimeCommentEntity> comments = this.animeCommentDao.findAll();
        comments.removeIf(entity -> !entity.getAnime_id().equals(animeId));
        return comments;
    }

    public Optional<AnimeComment> getCurrentUserAnimeCommentForASelectAnime(String currentUserLogin, Long animeId) {
        if (usersRepository.findByUsername(currentUserLogin).isPresent()) {
            return this.animeCommentDao.findById(new AnimeCommentId(usersRepository.findByUsername(currentUserLogin).get().getId(), animeId)).map(AnimeComment::new);
        } else {
            return Optional.empty();
        }
    }
}
