package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller.UserController;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ArticleRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.CommentRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ImageRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ArticleDto;
import javassist.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final UsersRepository usersRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final ImageRepository imageRepository;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ArticleService(UsersRepository usersRepository, ArticleRepository articleRepository, CommentRepository commentRepository, ImageRepository imageRepository) {
        this.usersRepository = usersRepository;
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
        this.imageRepository = imageRepository;
    }

    public ArticleDto getArticle(long id) {
        return new ArticleDto(this.articleRepository.getOne(id));
    }

    public List<ArticleDto> findAllArticles() {
        return this.articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    public List<ArticleDto> findFiveArticles() {
        return this.articleRepository.findTop5ByOrderByIdDesc().stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    public Long postArticle(ArticleDto articleDto) throws NotFoundException, NoUserFoundException {
        LocalDateTime now = LocalDateTime.now();
        this.articleRepository.save(
                new ArticleEntity(
                        articleDto.getTitle(),
                        articleDto.getBody(),
                        dtf.format(now),
                        this.usersRepository.findByUsername(UserController.getCurrentUserLogin()).orElseThrow(NoUserFoundException::new),
                        this.imageRepository.findFirstByOrderByIdDesc(),
                        articleDto.getCategory()
                ));
        return this.getLastArticleId();
    }

    public Long getLastArticleId() {
        List<ArticleEntity> ael = this.articleRepository.findAll();
        if (ael.size() > 0) {
            Long id = 1L;
            for (ArticleEntity ae : ael) {
                if (ae.getId() >= id) id = ae.getId();
            }
            return this.articleRepository.getOne(id).getId();
        } else {
            return 0L;
        }
    }

    public List<ArticleDto> getArticlesByCategoryId(int page, String query, Integer categoryId) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id");
        return this.articleRepository.findAllByCompleteResearch(query, categoryId, pageable).toList().stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    public List<ArticleDto> getArticlesByPage(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id");
        return this.articleRepository.findAll(pageable).toList().stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    public List<ArticleDto> getSimilarArticles(long category, long articleId) {
        return this.articleRepository.findTop5ByCategoryIdAndIdNot(Sort.by(Sort.Direction.DESC, "id"), category, articleId).stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void deleteArticle(long id) {
        this.commentRepository.deleteCommentEntitiesByArticleEntity(this.articleRepository.getOne(id));
        this.articleRepository.deleteById(id);
    }
}
