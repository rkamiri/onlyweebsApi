package fr.paris8univ.iut.csid.csidwebrepositorybase.core.model;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.CommentEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ListsEntity;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private UsersDto user;
    private String body;
    private String date;
    private AnimeEntity animeEntity;
    private ArticleEntity articleEntity;
    private ListsEntity listsEntity;

    public CommentDto() {
    }

    public CommentDto(CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.user = new UsersDto(commentEntity.getUserEntity());
        this.body = commentEntity.getBody();
        this.date = commentEntity.getDate();
        this.animeEntity = commentEntity.getAnimeEntity();
        this.articleEntity = commentEntity.getArticleEntity();
        this.listsEntity = commentEntity.getListsEntity();
    }
}
