package com.ucdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ucdb.exceptions.email.EmailNotFound;

@RestController
public class EmailController {

    @Autowired private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("UCDB");
        message.setText("Bem vindo ao sistema UCDB");
        message.setTo(email);
        message.setFrom("ucdbprojsw@gmail.com");

        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailNotFound("Email não existe!");
        }
    }
}
