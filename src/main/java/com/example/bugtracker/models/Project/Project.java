package com.example.bugtracker.models.Project;

import com.example.bugtracker.models.Issue.Issue;
import com.example.bugtracker.models.Person.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    protected String name;
    @OneToMany(mappedBy = "project")
    Set<Issue> issues= new HashSet<>();
    protected boolean enabled=true;
    protected Date dateCreated;
    protected String code;
    protected String description;
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    Person creator;

    public Project(Long id, ProjectDto projectDto) {
        this.id = id;
        this.name = projectDto.name;
        this.issues = projectDto.issues;
        this.code = projectDto.code;
        this.dateCreated = projectDto.dateCreated;
        this.description = projectDto.description;
        this.enabled = projectDto.enabled;
        this.creator= projectDto.creator;
    }
}
