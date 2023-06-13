package com.example.bugtracker.controllers;

import com.example.bugtracker.enums.Status;
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

    @GetMapping
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("projects1/index");

        modelAndView.addObject("projects", projectService.getAll());
        return modelAndView;
    }


    @GetMapping("{id}")
    public Project getOne(@PathVariable Long id){
        return projectService.getOne(id);
    }

    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("projects1/addProject");

        Project project = new Project();
        modelAndView.addObject("project", project);
        return modelAndView;
    }

    @PostMapping("/save")
    String save(@ModelAttribute Project project) {

        boolean isNew = project.getId() == null;
        boolean statusChanged = true;

        if (project.getId() != null) {
            statusChanged = project.getStatus() != projectService.getOne(project.getId()).getStatus() || project.getStatus() != null;
        }

        projectService.save(project);

        if (isNew) {
            return "redirect:/projects";
        } else if (statusChanged) {
            return "redirect:/projects";
        } else {
            return "redirect:/projects/edit/" + project.getId();
        }
    }


    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView= new ModelAndView("projects1/addProject");
        Project project= projectService.getOne(id);
        modelAndView.addObject("project",project);
        return modelAndView;
    }
    @GetMapping("/changeStatus/{id}")
    ModelAndView changeStatus(@PathVariable Long id){
        ModelAndView modelAndView= new ModelAndView("projects1/changeStatus");
        Project project= projectService.getOne(id);
        modelAndView.addObject("project",project);
        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
            void delete(@ModelAttribute Project project,@PathVariable Long id) {
        boolean found = project.getId()==null;

        if (!found){
            projectService.delete(id);
        }

    }

}
