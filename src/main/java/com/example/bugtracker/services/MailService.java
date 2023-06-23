package com.example.bugtracker.services;

import com.example.bugtracker.Mail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    final private JavaMailSender javaMailSender;
    private Mail mail;


    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void send(Mail mail) {
        this.mail = mail;
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(mail.getRecipient());
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setText(mail.getContent());

            mimeMessageHelper.addAttachment(mail.getAttachment().getOriginalFilename(), (DataSource) mail.getAttachment());

            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            System.out.println("Wysyłanie mejla nie powiodło się " + e);
        }
    }
}
