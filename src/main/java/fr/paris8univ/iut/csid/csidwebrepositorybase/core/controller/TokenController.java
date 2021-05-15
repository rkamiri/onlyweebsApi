package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.TokenEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.MailService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.TokenService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/security")
public class TokenController {

    private final MailService mailService;
    private final UsersService usersService;
    private final TokenService tokenService;

    public TokenController(MailService mailService, UsersService usersService, TokenService tokenService) {
        this.mailService = mailService;
        this.usersService = usersService;
        this.tokenService = tokenService;
    }

    @GetMapping("/change-password")
    public void sendMail() throws MessagingException {
        UsersEntity user = this.usersService.findUserEntityByUsername(UserController.getCurrentUserLogin());
        TokenEntity token = this.tokenService.createToken(user);
        this.mailService.sendEmail(
                user.getEmail(),
                token.getToken()
        );
    }
}
