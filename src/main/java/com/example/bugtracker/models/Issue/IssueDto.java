package com.example.bugtracker.models.Issue;

import com.example.bugtracker.models.Comment.Comment;
import com.example.bugtracker.models.Person.Person;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class IssueDto {

    Enum<Status> status;
    Enum<Priority> priority;
    Enum<Type> type;
    List<Enum>tags;
    String name;
    String descdription;
    String code;
    Person creator;
    Person assignee;
    Date dateCreated;
    Date lastUpdate;
    List<Comment> comments;
}
