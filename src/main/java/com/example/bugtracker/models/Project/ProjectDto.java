package com.example.bugtracker.models.Project;

import com.example.bugtracker.models.Issue.Issue;
import com.example.bugtracker.models.Person.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

public class ProjectDto {

    protected String name;
    protected LocalDate dateCreated;
    protected String code;
    protected String description;
    protected Person creator;

    public ProjectDto(String name, String code, String description, Person creator) {
        this.name = name;
        this.dateCreated = LocalDate.now();
        this.code = code;
        this.description = description;
        this.creator = creator;
    }
}
