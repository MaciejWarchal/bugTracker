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

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;



    public Iterable<Project> getAll(){
        return projectRepository.findAll();
    }

    public Project getOne(@PathVariable Long id){
        return projectRepository.findById(id).
                orElseThrow(ResourceNotFoundException::new);

    }

    public Project create(Project project){
        return projectRepository.save(project);
    };

    // do dokończenia
    public Project update(Long id,Project project){
        Project project1=getOne(id);
        return project1;
    }

    public void delete(Long id){
        projectRepository.deleteById(id);
    }

    public Project save(Project project){return projectRepository.save(project);}




}
