package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Image;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Users;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.exception.NoUserFoundException;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.MailService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;
    private final ImageController imageController;
    private final MailService mailService;

    @Autowired
    public UserController(UsersService usersService, ImageController imageController, MailService mailService) {
        this.usersService = usersService;
        this.imageController = imageController;
        this.mailService = mailService;
    }

    @GetMapping("/same-ip")
    public Boolean getUserSameIp(HttpServletRequest request) {
        return this.usersService.checkIpAddress(request.getRemoteAddr(), getCurrentUserLogin());
    }

    @GetMapping("/update/ip")
    public void updateIp(HttpServletRequest request) {
        this.usersService.updateIp(request.getRemoteAddr(), getCurrentUserLogin());
    }

    @GetMapping("/mail/send")
    public void sendMail() throws MessagingException {
        this.mailService.sendEmail(this.usersService.findUserEntityByUsername(getCurrentUserLogin()).getEmail());
    }

    @GetMapping("/current")
    public Optional<Users> getCurrentUser() {
        if (!getCurrentUserLogin().equals("anonymousUser")) {
            return this.usersService.findOneByLogin(getCurrentUserLogin());
        } else {
            return Optional.empty();
        }
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
        return login;
    }

    @PutMapping("/update")
    public Users updateCurrentUser(@RequestBody Users updatedUser) throws NoUserFoundException {
        return this.usersService.updateCurrentUser(updatedUser);
    }

    @GetMapping("/pp")
    public Image getUserProfilePicture() {
        return imageController.downloadImage(this.usersService.findUserEntityByUsername(getCurrentUserLogin()).getImage().getId());
    }
}
