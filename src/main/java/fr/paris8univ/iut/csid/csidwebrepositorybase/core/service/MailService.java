package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {
    private Session mailSession;
    final String username = "onlyweebsofficial@gmail.com";
    final String password = "onlyweebs6942069420";

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

    private MimeMessage draftEmailMessage() throws MessagingException {
        String recipients = "naelmez18@gmail.com, onlyweebsofficial@gmail.com";
        String emailSubject = "Test email subject";
        String emailBody = "This is an email sent by OnlyWeebs website.";
        MimeMessage emailMessage = new MimeMessage(mailSession);
        emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);
        return emailMessage;
    }

    public void sendEmail() throws MessagingException {
        this.setMailServerProperties();
        String emailHost = "smtp.gmail.com";
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, username, password);
        MimeMessage emailMessage = draftEmailMessage();
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully.");
    }
}