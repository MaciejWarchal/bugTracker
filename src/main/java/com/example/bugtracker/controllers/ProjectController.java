package com.example.bugtracker.controllers;

import com.example.bugtracker.enums.Status;
import com.example.bugtracker.filters.ProjectFilter;
import com.example.bugtracker.models.Issue.Issue;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.models.Project.ProjectDto;
import com.example.bugtracker.services.PersonService;
import com.example.bugtracker.services.ProjectService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Controller
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {
    // todo secure
    private final ProjectService projectService;
    private final PersonService personService;




    @GetMapping
    ModelAndView index(ProjectFilter filter) {
        ModelAndView modelAndView = new ModelAndView("projects1/index");

        modelAndView.addObject("projects", projectService.getAll(filter));
        return modelAndView;
    }


    @GetMapping("{id}")
    public Project getOne(@PathVariable Long id){
        return projectService.getOne(id);
    }

    @GetMapping("/create")
    ModelAndView create() {

        Iterable<Person> people = personService.getAll();
        ModelAndView modelAndView = new ModelAndView("projects1/addProject");


        Project project = new Project();
        modelAndView.addObject("project", project);
        modelAndView.addObject("people", people);
        return modelAndView;
    }

    @PostMapping("/save")
    String save(@ModelAttribute Project project) {


        boolean isNew = project.getId() == null;
        boolean statusChanged = true;
        boolean enabledChanged = true;

        if (project.getId() != null) {
            statusChanged = project.getStatus() != projectService.getOne(project.getId()).getStatus() || project.getStatus() != null;
        }
        if (project.getId() != null) {
            enabledChanged = project.isEnabled() != projectService.getOne(project.getId()).isEnabled() || project.isEnabled() != true;
        }

        projectService.save(project);

        if (isNew) {
            return "redirect:/projects";
        } else if (statusChanged) {
            return "redirect:/projects";
        } else if (enabledChanged) {
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

    @GetMapping("/delete/{id}")
    ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView= new ModelAndView("projects1/delete");
        Project project= projectService.getOne(id);
        modelAndView.addObject("project",project);
        return modelAndView;
    }

    public String contact() {
        return "contact";
    }

    @GetMapping("/list")
    ModelAndView modelAndView(@ModelAttribute ProjectFilter filter, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("projects1/listOfProjects");

        // Pobierz autentykację (zalogowanego użytkownika)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentname = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Sprawdź, czy autentykacja zawiera szczegóły użytkownika
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                currentname = userDetails.getUsername();
            }
        }

        // Przekazuj obecny użytkownik do widoku
        modelAndView.addObject("currentname", currentname);

        Page<Project> projects = projectService.getAll(filter.buildSpecification(), pageable);
        modelAndView.addObject("projects", projects);
        Person person = personService.getOne(personService.getByName(currentname).getId());
        modelAndView.addObject("person", person);

        modelAndView.addObject("filter", filter);
        return modelAndView;
    }



}
