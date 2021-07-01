package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    List<ArticleEntity> getArticleEntitiesByAuthor(UserEntity author);
    List<ArticleEntity> getArticleEntitiesByCategoryIdAndTitleLike(Pageable page, Long category, String title);
    @Query(value = "Select distinct a.id, a.title, a.body, a.created_at, a.author_id, a.cover_id, a.category_id from article a " +
            "where (:query is null or title like CONCAT('%',:query,'%')) and (:category is null or category_id like CONCAT('%',:category,'%'))",
            countQuery = "select count(*) from article a " +
                    "where (:query is null or title like CONCAT('%',:query,'%')) and (:category is null or category_id =:category",
            nativeQuery = true)
    Page<ArticleEntity> findAllByCompleteResearch(@Param("query") String query, @Param("category") Integer category, Pageable page);
    List<ArticleEntity> findTop5ByCategoryIdAndIdNot(Sort sort, long category, long articleId);
    List<ArticleEntity> findTop5ByOrderByIdDesc();
}
