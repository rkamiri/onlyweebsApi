package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    public final UsersRepository ur;

    public UsersService(UsersRepository ur) {
        this.ur = ur;
    }

    public Users findOneByLogin(String currentUserLogin) throws NoUserFoundException {
        return this.ur.findByUsername(currentUserLogin);
    }

    public Users updateCurrentUser(Users updatedUser) throws NoUserFoundException {
        return this.ur.updateCurrentUser(updatedUser);
    }
}
