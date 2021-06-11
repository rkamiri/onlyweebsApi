package fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.AnimeEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleCategoriesEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> getArticleEntitiesByAuthor(UsersEntity author);

    List<ArticleEntity> getArticleEntitiesByCategoryIdAndTitleLike(Pageable page, Long category, String title);

    @Query(value = "Select distinct a.id, a.title, a.body, a.created_at, a.author_id, a.cover_id, a.category_id from article a " +
                   "where (:query is null or title like CONCAT('%',:query,'%')) and (:category is null or category_id like CONCAT('%',:category,'%'))",
            countQuery = "select count(*) from article a " +
                         "where (:query is null or title like CONCAT('%',:query,'%')) and (:category is null or category_id like CONCAT('%',:category,'%'))",
            nativeQuery = true)
    Page<ArticleEntity> findAllByCompleteResearch(@Param("query")String query, @Param("category") Long category, Pageable page);

}
