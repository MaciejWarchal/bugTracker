package com.example.bugtracker.models.Comment;

import com.example.bugtracker.models.Person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto {

    Date dateCreated;
    Person author;
    String content;



}
