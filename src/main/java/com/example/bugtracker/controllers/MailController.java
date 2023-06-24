package com.example.bugtracker.controllers;

import com.example.bugtracker.Mail;
import com.example.bugtracker.services.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mails")
public class MailController {

    final private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping
    String getForm() {
        return "projects1/indexMail";
    }

    @PostMapping
    String sendMail(@ModelAttribute Mail mail) {
        mailService.send(mail);
        return "projects1/indexMail";
    }


}
