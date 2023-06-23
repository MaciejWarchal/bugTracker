package com.example.bugtracker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    int port=857;
    /*@Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Configure mailSender properties
        mailSender.setHost("your_smtp_host");
        mailSender.setPort(mailSender.setPort(port));
        mailSender.setUsername("your_username");
        mailSender.setPassword("your_password");
        mailSender.setJavaMailProperties(getMailProperties());
        return mailSender;
    }*/

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        return properties;
    }
}


