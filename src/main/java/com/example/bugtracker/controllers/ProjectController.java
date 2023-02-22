package com.example.bugtracker.controllers;

import com.example.bugtracker.models.Issue.Issue;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.models.Project.ProjectDto;
import com.example.bugtracker.services.ProjectService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {
    // todo secure
    private final ProjectService projectService;

    @GetMapping("/list")
    public Iterable<Project> getAll(){
        return projectService.getAll();
    }

    @GetMapping("{id}")
    public Project getOne(@PathVariable Long id){
        return projectService.getOne(id);
    }

    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("projects/addProject");

        Project project = new Project();
        modelAndView.addObject("project", project);

        return modelAndView;
    }








}
