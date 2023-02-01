package com.example.bugtracker.models.Comment;

import com.example.bugtracker.models.Person.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Date dateCreated;
    @ManyToOne
    Person author;
    String content;

    public Comment(Long id, CommentDto commentDto) {
        this.id = id;
        this.dateCreated = commentDto.dateCreated;
        //this.author = commentDto.author;
        this.content = commentDto.content;
    }


}
