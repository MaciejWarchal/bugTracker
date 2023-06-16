package com.example.bugtracker.services;

import com.example.bugtracker.exceptions.ResourceNotFoundException;
import com.example.bugtracker.filters.ProjectFilter;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.models.Project.ProjectDto;
import com.example.bugtracker.repositories.PersonRepository;
import com.example.bugtracker.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;




    public Iterable<Project> getAll(ProjectFilter filter) {
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

    public Iterable<Project> getAll() {
        return projectRepository.findAll();
    }

    public Page<Project> getAll(Specification<Project> specification, Pageable pageable) {
        return projectRepository.findAll(specification, pageable);
    }

    public Project create(Project project){
        return projectRepository.save(project);
    };



    public void delete(Long id){
        projectRepository.deleteById(id);
    }

    public Project save(Project project){return projectRepository.save(project);}




}
