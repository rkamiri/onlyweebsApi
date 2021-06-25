package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import javassist.NotFoundException;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    public final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<Users> findOneByLogin(String currentUserLogin) throws NotFoundException {
        try {
            return this.usersRepository.findByUsername(currentUserLogin).map(Users::new);
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("findOneByLogin error");
        }
    }

    public UsersEntity findUserEntityByUsername(String currentUserLogin) {
        return this.usersRepository.findUserEntityByUsername(currentUserLogin);
    }

    public Users updateCurrentUser(Users updatedUser) throws NoUserFoundException {
        return this.usersRepository.updateCurrentUser(updatedUser);
    }

    public Boolean checkIpAddress(String remoteAddress, String currentUserLogin) {
        return this.usersRepository.checkIpAddress(remoteAddress, currentUserLogin);
    }

    public void updateIp(String newIp, String currentUserLogin) {
        this.usersRepository.updateIp(newIp, currentUserLogin);
    }

    public void deleteUser(UsersEntity usersEntity) throws NoUserFoundException {
        this.usersRepository.deleteUser(usersEntity);
    }

    public Users getUser(long id) {
        return new Users(this.usersRepository.getUser(id));
    }
}
