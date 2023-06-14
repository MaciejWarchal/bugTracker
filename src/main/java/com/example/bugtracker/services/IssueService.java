package com.example.bugtracker.services;

import com.example.bugtracker.exceptions.ResourceNotFoundException;
import com.example.bugtracker.models.Issue.Issue;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.models.Project.ProjectDto;
import com.example.bugtracker.repositories.IssueRepository;
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
public class IssueService {

    private final IssueRepository issueRepository;



    public Iterable<Issue> getAll(){
        return issueRepository.findAll();
    }

    public Issue getOne(@PathVariable Long id){
        return issueRepository.findById(id).orElse(null);

    }

    public Issue create(Issue issue){
        return issueRepository.save(issue);
    };


    public Issue update(Long id, Issue issue){
        Issue issue1= getOne(id);
        return issue;
    }

    public void delete(Long id){
        issueRepository.deleteById(id);
    }

    public Issue save(Issue issue){return issueRepository.save(issue);}




}
