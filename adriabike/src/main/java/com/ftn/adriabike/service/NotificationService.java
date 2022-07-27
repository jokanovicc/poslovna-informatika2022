package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@EnableAutoConfiguration
@Service
public class NotificationService {


    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(Korisnik korisnik, String subject, Integer brojPorudzbine) throws MailException {
        //send email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(korisnik.getEmail());
        mail.setFrom("adriabikestore@gmail.com");
        mail.setSubject("ADRIA BIKE DOO - Novi Sad: Поружбина број: "+brojPorudzbine);
        mail.setText(subject);

        javaMailSender.send(mail);

    }


}
