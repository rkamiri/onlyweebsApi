package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeCommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.AnimeCommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeCommentDao extends JpaRepository<AnimeCommentEntity, AnimeCommentId> {
    void deleteAnimeCommentEntityByAnimeIdAndUsersEntity(long animeId, UsersEntity usersEntity);
}
