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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;



    public Iterable<Issue> getAll() {
        List<Issue> issues = new ArrayList<>();

        for (Issue issue : issueRepository.findAll()) {

            if (issue.getEnabled()==true) {
                issues.add(issue);
            }
        }

        return issues;
    }

    public Issue getOne(@PathVariable Long id){
        Issue issue= issueRepository.findById(id).orElse(null);
        if(issue.getEnabled()==true){
            return issue;
        } else {
            return null;
        }

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
