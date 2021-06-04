package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.*;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public class UsersRepository {

    private final UsersDao usersDao;
    private final AuthDao authDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ListsDao listsDao;
    private final IsListedInDao isListedIdDao;
    private final CommentDao commentDao;
    private final RatingDao ratingDao;
    private final ArticleDao articleDao;
    private final ImageDao imageDao;

    public UsersRepository(UsersDao usersDao, AuthDao authDao, BCryptPasswordEncoder bCryptPasswordEncoder, ListsDao listsDao, IsListedInDao isListedIdDao, CommentDao commentDao, RatingDao ratingDao, ArticleDao articleDao, ImageDao imageDao) {
        this.usersDao = usersDao;
        this.authDao = authDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.listsDao = listsDao;
        this.isListedIdDao = isListedIdDao;
        this.commentDao = commentDao;
        this.ratingDao = ratingDao;
        this.articleDao = articleDao;
        this.imageDao = imageDao;
    }

    public Optional<Users> findByUsername(String username) {
        return this.usersDao.findByUsername(username).map(Users::new);
    }

    public UsersEntity findUserEntityByUsername(String username) {
        return this.usersDao.findByUsername(username).orElseThrow();
    }

    public Users updateCurrentUser(Users updatedUser) throws NoUserFoundException {
        UsersEntity current = this.usersDao.findById(updatedUser.getId()).orElseThrow(NoUserFoundException::new);

        if (updatedUser.getUsername() != null || !Objects.equals(updatedUser.getUsername(), current.getUsername())) {
            AuthEntity currAuthDao = this.authDao.findById(current.getUsername()).orElseThrow();
            this.authDao.delete(currAuthDao);
            currAuthDao.setUsername(updatedUser.getUsername());
            current.setUsername(updatedUser.getUsername());
            this.usersDao.save(current);
            this.authDao.save(currAuthDao);
        }
        if (updatedUser.getEmail() != null || !Objects.equals(updatedUser.getEmail(), current.getEmail())) {
            current.setEmail(updatedUser.getEmail());
            this.usersDao.save(current);
        }
        if (updatedUser.getFirstname() != null || !Objects.equals(updatedUser.getFirstname(), current.getFirstname())) {
            current.setFirstname(updatedUser.getFirstname());
            this.usersDao.save(current);
        }
        if (updatedUser.getLastname() != null || !Objects.equals(updatedUser.getLastname(), current.getLastname())) {
            current.setLastname(updatedUser.getLastname());
            this.usersDao.save(current);
        }
        if (updatedUser.getPassword() != null) {
            current.setPassword(this.bCryptPasswordEncoder.encode(updatedUser.getPassword()));
            this.usersDao.save(current);
        }
        if (updatedUser.getBio() != null || !Objects.equals(updatedUser.getBio(), current.getBio())) {
            current.setBio(updatedUser.getBio());
            this.usersDao.save(current);
        }

        return new Users(this.usersDao.findById(updatedUser.getId()).orElseThrow(NoUserFoundException::new));
    }

    public Boolean checkIpAddress(String remoteIp, String userLogin) {
        return bCryptPasswordEncoder.matches(remoteIp, this.findUserEntityByUsername(userLogin).getIp());
    }

    public void updateIp(String newIp, String currentUserLogin) {
        UsersEntity usersEntity = this.findUserEntityByUsername(currentUserLogin);
        usersEntity.setIp(bCryptPasswordEncoder.encode(newIp));
        this.usersDao.save(usersEntity);
    }

    @Transactional
    public void deleteUser(UsersEntity usersEntity) {
        List<ListsEntity> lists = this.listsDao.getListsEntitiesByIsOwnedBy(usersEntity.getId());
        int[] intArray = {1,2,3};
        for (ListsEntity list: lists) {
            this.isListedIdDao.deleteIsListedInEntitiesByListId(list.getId());
        }
        this.listsDao.deleteInBatch(lists);
        this.commentDao.deleteInBatch(this.commentDao.findCommentEntitiesByUsersEntity(usersEntity));
        this.ratingDao.deleteInBatch(this.ratingDao.getRatingEntitiesByUserId(usersEntity.getId()));
        if (Arrays.stream(intArray).noneMatch(i -> i ==  usersEntity.getImage().getId())) {
            this.imageDao.delete(usersEntity.getImage());
        }
        for (ArticleEntity articleEntity : this.articleDao.getArticleEntitiesByAuthor(usersEntity)) {
            articleEntity.setAuthor(this.usersDao.findByUsername("deleted").orElseThrow());
        }
        this.authDao.delete(this.authDao.getOne(usersEntity.getUsername()));
        this.usersDao.delete(usersEntity);
    }
}
