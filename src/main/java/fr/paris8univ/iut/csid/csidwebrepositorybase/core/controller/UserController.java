package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Image;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.UsersService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;
    private final ImageController imageController;

    @Autowired
    public UserController(UsersService usersService, ImageController imageController) {
        this.usersService = usersService;
        this.imageController = imageController;
    }

    @GetMapping("/same-ip")
    public Boolean getUserSameIp(HttpServletRequest request) throws NotFoundException {
        return this.usersService.checkIpAddress(request.getRemoteAddr(), getCurrentUserLogin());
    }

    @GetMapping("/update/ip")
    public void updateIp(HttpServletRequest request) throws NotFoundException {
        this.usersService.updateIp(request.getRemoteAddr(), getCurrentUserLogin());
    }

    @GetMapping("/current")
    public Optional<Users> getCurrentUser() throws NotFoundException {
        try {
            if (!getCurrentUserLogin().equals("anonymousUser")) {
                return this.usersService.findOneByLogin(getCurrentUserLogin());
            } else {
               return Optional.empty();
            }
        } catch (Exception e) {
            throw new NotFoundException("getCurrentUser error");
        }
    }

    public static String getCurrentUserLogin() throws NotFoundException {
        try {
            org.springframework.security.core.context.SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            String login = null;
            if (authentication != null)
                if (authentication.getPrincipal() instanceof UserDetails)
                    login = ((UserDetails) authentication.getPrincipal()).getUsername();
                else if (authentication.getPrincipal() instanceof String)
                    login = (String) authentication.getPrincipal();
            return login;
        } catch (Exception e) {
            throw new NotFoundException("getCurrentUserLogin error");
        }
    }

    @PutMapping("/update")
    public Users updateCurrentUser(@RequestBody Users updatedUser) throws NoUserFoundException {
        return this.usersService.updateCurrentUser(updatedUser);
    }

    @GetMapping("/pp")
    public Image getUserProfilePicture() throws NotFoundException {
        return imageController.downloadImage(this.usersService.findUserEntityByUsername(getCurrentUserLogin()).getImage().getId());
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount() throws NotFoundException {
        usersService.deleteUser(this.usersService.findUserEntityByUsername(getCurrentUserLogin()));
    }
}
