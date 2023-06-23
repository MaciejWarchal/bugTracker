package com.example.bugtracker;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class Mail {

    String recipient;
    String subject;
    String content;

    MultipartFile attachment;

}