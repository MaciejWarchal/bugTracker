package com.example.bugtracker.models.Issue;

import com.example.bugtracker.models.Comment.Comment;
import com.example.bugtracker.models.Person.Person;

import java.util.Date;
import java.util.List;

public class Issue {

    Long id;
    Enum<Status> status;
    Enum<Priority> priority;
    Enum<Type> type;
    List<Enum> tags;
    String name;
    String descdription;
    String code;
    Person creator;
    Person assignee;
    Date dateCreated;
    Date lastUpdate;
    List<Comment> comments;

    public Issue(Long id, IssueDto issueDto) {
        this.id = id;
        this.status = issueDto.status;
        this.priority = issueDto.priority;
        this.type = issueDto.type;
        this.tags = issueDto.tags;
        this.name = issueDto.name;
        this.descdription = issueDto.descdription;
        this.code = issueDto.code;
        this.creator = issueDto.creator;
        this.assignee = issueDto.assignee;
        this.dateCreated = issueDto.dateCreated;
        this.lastUpdate = issueDto.lastUpdate;
        this.comments = issueDto.comments;
    }
}
