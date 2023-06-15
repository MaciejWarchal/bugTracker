package com.example.bugtracker.services;

import com.example.bugtracker.exceptions.ResourceNotFoundException;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.models.Project.ProjectDto;
import com.example.bugtracker.repositories.PersonRepository;
import com.example.bugtracker.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;




    public Iterable<Project> getAll() {
        List<Project> projects = new ArrayList<>();

        for (Project project : projectRepository.findAll()) {

            if (project.isEnabled()) {
                projects.add(project);
            }
        }

        return projects;
    }

    public Project getOne(@PathVariable Long id){
        Project project= projectRepository.findById(id).orElse(null);
        if(project.isEnabled()){
            return project;
        } else {
            return null;
        }

    }

    public Project create(Project project){
        return projectRepository.save(project);
    };



    public void delete(Long id){
        projectRepository.deleteById(id);
    }

    public Project save(Project project){return projectRepository.save(project);}




}
