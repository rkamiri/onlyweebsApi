package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

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
        mailSession = Session.getInstance(emailProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }

    private MimeMessage draftEmailMessage(String recipient, String token) throws MessagingException {
        // String recipients = "naelmez18@gmail.com, nmz94140@gmail.com";
        String emailSubject = "Test email subject";
        String emailBody = onlyweebs + "password-update/" + token;
        MimeMessage emailMessage = new MimeMessage(mailSession);
        emailMessage.setRecipients(Message.RecipientType.TO, recipient);
        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);
        return emailMessage;
    }

    public void sendEmail(String recipient, String token) throws MessagingException {
        this.setMailServerProperties();
        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, username, password);
        MimeMessage emailMessage = draftEmailMessage(recipient, token);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
    }
}