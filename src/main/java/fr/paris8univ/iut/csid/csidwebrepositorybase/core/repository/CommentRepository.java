package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller.UserController;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.AnimeDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.CommentDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Comment;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeCommentId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository {

    private final CommentDao commentDao;
    private final AnimeDao animeDao;
    public final UsersRepository usersRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public CommentRepository(CommentDao commentDao, AnimeDao animeDao, UsersRepository usersRepository) {
        this.commentDao = commentDao;
        this.animeDao = animeDao;
        this.usersRepository = usersRepository;
    }

    public void putAComment(Comment comment) {
        // List<CommentEntity> animeCommentEntities = this.commentDao.findAll();
        //animeCommentEntities.removeIf(x -> !x.getUsersEntity().getId().equals(user.getId()));
        // animeCommentEntities.removeIf(x -> !x.getAnimeEntity().getId().equals(comment.getId()));
        LocalDateTime now = LocalDateTime.now();
        UsersEntity user = this.usersRepository.findUserEntityByUsername(UserController.getCurrentUserLogin());
        AnimeEntity animeEntity = comment.getAnimeEntity();
        List<CommentEntity> animeCommentEntities = this.commentDao.findCommentEntitiesByAnimeEntityAndUsersEntity(animeEntity, user);
        if (animeCommentEntities.size() > 0) {
            animeCommentEntities.get(0).setBody(comment.getBody());
            animeCommentEntities.get(0).setDate(dtf.format(now));
            commentDao.save(animeCommentEntities.get(0));
        } else {
            this.commentDao.save(new CommentEntity(user, comment.getBody(), dtf.format(now), comment.getAnimeEntity()));
        }
    }

    public List<CommentEntity> getAllCommentsByAnimeId(long animeId) {
        List<CommentEntity> comments = this.commentDao.findAll();
        comments.removeIf(entity -> !entity.getAnimeEntity().getId().equals(animeId));
        return comments;
    }

    public Optional<Comment> getCurrentUserAnimeCommentForASelectAnime(String currentUserLogin, Long animeId) {
        if (usersRepository.findByUsername(currentUserLogin).isPresent()) {
            return this.commentDao.findById(new AnimeCommentId(usersRepository.findByUsername(currentUserLogin).get().getId(), animeId)).map(Comment::new);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public void deleteComment(String currentUserLogin, long animeId) {
        this.commentDao.deleteCommentEntityByAnimeEntityAndUsersEntity(this.animeDao.getOne(animeId), this.usersRepository.findUserEntityByUsername(currentUserLogin));
    }
}
