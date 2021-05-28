package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Service
public class MailService {

    private Session mailSession;

    @Value("${ow.gmail.username}")
    private String username;

    @Value("${ow.gmail.password}")
    private String password;

    @Value("${ow.link}")
    private String onlyweebs;

    public MailService() {
    }

    private void setMailServerProperties() {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.host", "smtp.gmail.com");
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        emailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        emailProperties.put("mail.smtp.ssl.protocols", "TLSv1.3");
        mailSession = Session.getInstance(emailProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    private MimeMessage draftEmailMessage(String recipient, String token) throws MessagingException, IOException {
        String emailSubject = "Reset your password";
        String emailBody = new String(Files.readAllBytes(Paths.get("password-update.html")));
        emailBody = addUrlToHtml(emailBody, token);
        MimeMessage emailMessage = new MimeMessage(mailSession);
        emailMessage.setRecipients(Message.RecipientType.TO, recipient);
        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody, "utf-8", "html");
        return emailMessage;
    }

    public void sendEmail(String recipient, String token) throws MessagingException, IOException {
        this.setMailServerProperties();
        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, username, password);
        MimeMessage emailMessage = draftEmailMessage(recipient, token);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }

    public String addUrlToHtml(String str, String token) {
        String tokenUrl = onlyweebs + "password-update/" + token;
        return str.substring(0, str.indexOf("href=\"#\"")+6) + tokenUrl + str.substring(str.indexOf("href=\"#\"")+7);
    }
}