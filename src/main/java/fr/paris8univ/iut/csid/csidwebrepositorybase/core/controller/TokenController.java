package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.TokenEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.UpdatePassword;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.MailService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.TokenService;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public String sendMail() throws MessagingException {
        UsersEntity user = this.usersService.findUserEntityByUsername(UserController.getCurrentUserLogin());
        TokenEntity token = this.tokenService.createToken(user);
        return this.mailService.sendEmail(user.getEmail(), token.getToken());
    }
  
    @PostMapping("/change-password")
    public ResponseEntity<Boolean> putPassword(@RequestBody UpdatePassword newPassword) {
        if (this.tokenService.putPassword(newPassword)==null) {
            return ResponseEntity.status(401).body(false);
        };
        return ResponseEntity.ok(true);
    }
}
