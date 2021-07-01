package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller.UserController;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.CommentDto;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final AnimeRepository animeRepository;
    private final ListsRepository listsRepository;
    private final UsersRepository usersRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository, AnimeRepository animeRepository, ListsRepository listsRepository, UsersRepository usersRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.animeRepository = animeRepository;
        this.listsRepository = listsRepository;
        this.usersRepository = usersRepository;
    }

    public void putComment(CommentDto commentDto) throws NotFoundException, NoUserFoundException {
        LocalDateTime now = LocalDateTime.now();
        UserEntity user = this.usersRepository.findByUsername(UserController.getCurrentUserLogin()).orElseThrow(NoUserFoundException::new);
        List<CommentEntity> commentEntities;
        int whichTypeOfComment;
        if (commentDto.getArticleEntity() == null && commentDto.getListsEntity() == null) {
            AnimeEntity animeEntity = commentDto.getAnimeEntity();
            commentEntities = this.commentRepository.findCommentEntitiesByAnimeEntityAndUserEntity(animeEntity, user);
            whichTypeOfComment = 0;
        } else if (commentDto.getAnimeEntity() == null && commentDto.getListsEntity() == null) {
            ArticleEntity articleEntity = commentDto.getArticleEntity();
            commentEntities = this.commentRepository.findCommentEntitiesByArticleEntityAndUserEntity(articleEntity, user);
            whichTypeOfComment = 1;
        } else {
            ListsEntity listsEntity = commentDto.getListsEntity();
            commentEntities = this.commentRepository.findCommentEntitiesByListsEntityAndUserEntity(listsEntity, user);
            whichTypeOfComment = 2;
        }
        if (commentEntities.size() > 0) {
            commentEntities.get(0).setBody(commentDto.getBody());
            commentEntities.get(0).setDate(dtf.format(now));
            commentRepository.save(commentEntities.get(0));
        } else {
            CommentEntity commentEntity = new CommentEntity(user, commentDto.getBody(), dtf.format(now));
            if (whichTypeOfComment == 0) {
                commentEntity.setAnimeEntity(commentDto.getAnimeEntity());
            } else if (whichTypeOfComment == 1) {
                commentEntity.setArticleEntity(commentDto.getArticleEntity());
            } else {
                commentEntity.setListsEntity(commentDto.getListsEntity());
            }
            this.commentRepository.save(commentEntity);
        }
    }

    public List<CommentDto> getComments(long objectId, int type) {
        List<CommentEntity> commentEntities = new ArrayList<>();
        List<CommentDto> commentDtoList = new ArrayList<>();
        if (type == 0) {
            commentEntities = this.commentRepository.findCommentEntitiesByAnimeEntity(animeRepository.findById(objectId).get());
        } else if (type == 1) {
            commentEntities = this.commentRepository.findCommentEntitiesByArticleEntity(articleRepository.findById(objectId).get());
        } else {
            commentEntities = this.commentRepository.findCommentEntitiesByListsEntity(listsRepository.findById(objectId).get());
        }
        for (CommentEntity commentEntity : commentEntities) {
            commentDtoList.add(new CommentDto(commentEntity));
        }
        return commentDtoList;
    }

    @Transactional
    public void deleteCommentAsUser(String currentUserLogin, long entityId, int type) {
        this.deleteComment(this.usersRepository.findByUsername(currentUserLogin).orElseThrow(), entityId, type);
    }

    @Transactional
    public void deleteCommentAsAdmin(long userId, long entityId, int type) {
        this.deleteComment(this.usersRepository.findById(userId).orElseThrow(), entityId, type);
    }

    @Transactional
    public void deleteComment(UserEntity owner, long id, int type) {
        if (type==0) {
            this.commentRepository.deleteCommentEntityByAnimeEntityAndUserEntity(this.animeRepository.getOne(id), owner);
        } else if (type == 1){
            this.commentRepository.deleteCommentEntityByArticleEntityAndUserEntity(this.articleRepository.getOne(id), owner);
        } else {
            this.commentRepository.deleteCommentEntityByListsEntityAndUserEntity(this.listsRepository.getOne(id), owner);

        }
    }
}
