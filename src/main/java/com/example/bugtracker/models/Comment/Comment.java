package com.example.bugtracker.models.Comment;

import com.example.bugtracker.models.Person.Person;

import java.util.Date;

public class Comment {
    Long id;
    Date dateCreated;
    Person author;
    String content;

    public Comment(Long id, CommentDto commentDto) {
        this.id = id;
        this.dateCreated = commentDto.dateCreated;
        this.author = commentDto.author;
        this.content = commentDto.content;
    }


}
