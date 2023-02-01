package com.example.bugtracker.models.Issue;

import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.models.Comment.Comment;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor

public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Enum<Status> status;
    private Enum<Priority> priority;
    private Enum<Type> type;
    @ElementCollection(targetClass = Enum.class)
    @JoinTable(name = "tableOfTags",joinColumns = @JoinColumn(name = "issue_id"))
    @Column(name = "tag",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    List<Enum> tags;
    private String name;
    private String descdription;
    @ManyToOne
    @JoinTable(name = "project_issues",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "issue_id"))
    private Project project;
    private String code;
    @ManyToOne
            @JoinColumn(name = "creator_id", nullable = false)
    private Person creator;
    @ManyToMany
            @JoinTable(name = "tableOfAssignedPeople",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "assignedPerson_id"))
    @JsonIgnoreProperties("issue")
    private Set<Person> peopleAssigned;
    private Date dateCreated;
    private Date lastUpdate;
    @OneToMany
            @JsonIgnoreProperties("issue")
    private List<Comment> comments;

    public Issue(Long id, IssueDto issueDto) {
        this.id = id;
        this.status = issueDto.status;
        this.priority = issueDto.priority;
        this.type = issueDto.type;
        //this.tags = issueDto.tags;
        this.name = issueDto.name;
        this.descdription = issueDto.descdription;
        this.code = issueDto.code;
        //this.creator = issueDto.creator;
        //this.assignee = issueDto.assignee;
        this.dateCreated = issueDto.dateCreated;
        this.lastUpdate = issueDto.lastUpdate;
        //this.comments = issueDto.comments;
    }
}
