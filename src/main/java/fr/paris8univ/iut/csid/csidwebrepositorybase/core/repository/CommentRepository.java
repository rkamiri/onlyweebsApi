package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, AnimeCommentId> {
    void deleteCommentEntityByAnimeEntityAndUserEntity(AnimeEntity animeEntity, UserEntity userEntity);

    void deleteCommentEntitiesByListsEntity(ListsEntity listsEntity);

    void deleteCommentEntitiesByArticleEntity(ArticleEntity articleEntity);

    void deleteCommentEntityByArticleEntityAndUserEntity(ArticleEntity articleEntity, UserEntity userEntity);

    void deleteCommentEntityByListsEntityAndUserEntity(ListsEntity listsEntity, UserEntity userEntity);

    List<CommentEntity> findCommentEntitiesByAnimeEntityAndUserEntity(AnimeEntity animeEntity, UserEntity userEntity);

    List<CommentEntity> findCommentEntitiesByArticleEntityAndUserEntity(ArticleEntity articleEntity, UserEntity userEntity);

    List<CommentEntity> findCommentEntitiesByListsEntityAndUserEntity(ListsEntity listsEntity, UserEntity userEntity);

    List<CommentEntity> findCommentEntitiesByArticleEntity(ArticleEntity articleEntity);

    List<CommentEntity> findCommentEntitiesByAnimeEntity(AnimeEntity animeEntity);

    List<CommentEntity> findCommentEntitiesByListsEntity(ListsEntity listsEntity);

    long count();
}
