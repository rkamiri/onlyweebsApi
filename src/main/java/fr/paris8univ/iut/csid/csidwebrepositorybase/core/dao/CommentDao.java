package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends JpaRepository<CommentEntity, AnimeCommentId> {
    void deleteCommentEntityByAnimeEntityAndUsersEntity(AnimeEntity animeEntity, UsersEntity usersEntity);
    void deleteCommentEntityByArticleEntityAndUsersEntity(ArticleEntity articleEntity, UsersEntity usersEntity);
    List<CommentEntity> findCommentEntitiesByAnimeEntityAndUsersEntity(AnimeEntity animeEntity, UsersEntity usersEntity);
    List<CommentEntity> findCommentEntitiesByArticleEntityAndUsersEntity(ArticleEntity articleEntity, UsersEntity usersEntity);
    List<CommentEntity> findCommentEntitiesByArticleEntity(ArticleEntity articleEntity);
    List<CommentEntity> findCommentEntitiesByAnimeEntity(AnimeEntity animeEntity);
}
