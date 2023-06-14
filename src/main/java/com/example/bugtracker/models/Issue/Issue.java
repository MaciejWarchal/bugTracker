package com.example.bugtracker.models.Issue;

import com.example.bugtracker.enums.Status;
import com.example.bugtracker.models.Comment.Comment;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    protected Status status=Status.ToDo;
    @Enumerated(EnumType.STRING)
    protected Priority priority= Priority.Low;
    @Enumerated(EnumType.STRING)
    protected Type type=Type.Task;


    /*@ElementCollection(targetClass = Enum.class)
    @JoinTable(name = "tableOfTags",joinColumns = @JoinColumn(name = "issue_id"))
    @Column(name = "tag",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    List<Enum> tags;*/
    private String name;
    private String descdription;
    @ManyToOne
    @JoinTable(name = "project_issues",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "issue_id"))
    @JsonIgnoreProperties("issues")
    private Project project;

    private String code;
    @ManyToOne
            @JoinColumn(name = "creator_id", nullable = false)
    private Person creator;
    /*@ManyToMany
            @JoinTable(name = "tableOfAssignedPeople",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "assignedPerson_id"))
    @JsonIgnoreProperties("issue")
    private Set<Person> peopleAssigned;*/

    private Date dateCreated;
    private Date lastUpdate;
    @OneToMany
            @JsonIgnoreProperties("issue")
    private List<Comment> comments;

    private Boolean enabled=true;


}
