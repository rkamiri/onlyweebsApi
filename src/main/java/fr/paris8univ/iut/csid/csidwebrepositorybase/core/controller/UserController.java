package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/current")
    public Users getCurrentUser() throws NoUserFoundException {
        return this.usersService.findOneByLogin(getCurrentUserLogin());
    }

    public static String getCurrentUserLogin() {
        org.springframework.security.core.context.SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String login = null;
        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserDetails)
                login = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
                login = (String) authentication.getPrincipal();
        System.out.println(login);
        return login;
    }

    @PutMapping("/update")
    public Users updateCurrentUser(@RequestBody Users updatedUser) throws NoUserFoundException {
        System.out.println("here");
        return this.usersService.updateCurrentUser(updatedUser);
    }
}
