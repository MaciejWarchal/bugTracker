package com.example.bugtracker.models.Project;

import com.example.bugtracker.enums.Status;
import com.example.bugtracker.models.Issue.Issue;
import com.example.bugtracker.models.Person.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    @Size(min = 5, max = 255)
    @Column(nullable = false, unique = true)
    protected String name;

    @OneToMany(mappedBy = "project")
    @JsonIgnoreProperties("project")
    protected Set<Issue> issues;

    protected boolean enabled=true;
    protected Date dateCreated;
    protected String code;
    protected String description;
    protected Date updated;
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = true)
    Person creator;
    @Enumerated(EnumType.STRING)
    protected Status status=Status.ToDo;
    public Project(String name, String code, String description, Person creator) {
        this.name = name;
        this.dateCreated = Date.from(Instant.now());
        this.code = code;
        this.description = description;
        this.creator = creator;
        this.status = Status.ToDo;
    }


}
