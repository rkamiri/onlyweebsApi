package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<CommentEntity, AnimeCommentId> {
    void deleteCommentEntitiesByListsEntity(ListsEntity listsEntity);
    void deleteCommentEntitiesByArticleEntity(ArticleEntity articleEntity);
    void deleteCommentEntityByAnimeEntityAndUsersEntity(AnimeEntity animeEntity, UsersEntity usersEntity);
    void deleteCommentEntityByArticleEntityAndUsersEntity(ArticleEntity articleEntity, UsersEntity usersEntity);
    void deleteCommentEntityByListsEntityAndUsersEntity(ListsEntity listsEntity, UsersEntity usersEntity);
    List<CommentEntity> findCommentEntitiesByAnimeEntityAndUsersEntity(AnimeEntity animeEntity, UsersEntity usersEntity);
    List<CommentEntity> findCommentEntitiesByArticleEntityAndUsersEntity(ArticleEntity articleEntity, UsersEntity usersEntity);
    List<CommentEntity> findCommentEntitiesByListsEntityAndUsersEntity(ListsEntity listsEntity, UsersEntity usersEntity);
    List<CommentEntity> findCommentEntitiesByArticleEntity(ArticleEntity articleEntity);
    List<CommentEntity> findCommentEntitiesByAnimeEntity(AnimeEntity animeEntity);
    List<CommentEntity> findCommentEntitiesByListsEntity(ListsEntity listsEntity);
}
