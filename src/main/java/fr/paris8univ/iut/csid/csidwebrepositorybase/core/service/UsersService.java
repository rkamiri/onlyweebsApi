package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    public final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<Users> findOneByLogin(String currentUserLogin) {
        return this.usersRepository.findByUsername(currentUserLogin);
    }

    public UsersEntity findUserEntityByUsername(String currentUserLogin) {
        return this.usersRepository.findUserEntityByUsername(currentUserLogin);
    }

    public Users updateCurrentUser(Users updatedUser) throws NoUserFoundException {
        return this.usersRepository.updateCurrentUser(updatedUser);
    }

    public void checkIpAddress(String remoteAddr, String currentUserLogin) {
        this.usersRepository.checkIpAddress(remoteAddr, currentUserLogin);
    }
}
