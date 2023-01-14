package com.example.bugtracker.models.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String code;
    private String description;
    private boolean enabled=true;


}
