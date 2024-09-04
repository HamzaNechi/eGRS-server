package com.orange.orangegrs.utils.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String toEmail){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("nechihamza114@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("eGRS : Réinitialiser votre mot de passe");
            String encryptEmail = toEmail;
            /*try {
                EncryptionService encryptionService = new EncryptionService();
                encryptEmail = encryptionService.encrypt(toEmail);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }*/
            String htmlMsg = "<html><body><br><br><center><h2>Réinitialiser votre mot de passe</h2><p>Veuillez cliquer sur le lien ci-dessous pour réinitialiser votre mot de passe.</p> <br><a href='http://localhost:4200/auth/reset?email="+encryptEmail+"'>Cliquez ici</a></center></body></html>";
            message.setContent(htmlMsg, "text/html");
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("Erreur emai : "+ e.getMessage());
        }

        System.out.println("Message envoyé par email");
    }
}
