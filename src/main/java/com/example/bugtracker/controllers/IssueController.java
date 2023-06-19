package com.example.bugtracker.controllers;

import com.example.bugtracker.models.Issue.Issue;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.services.IssueService;
import com.example.bugtracker.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/issues")
@AllArgsConstructor
public class IssueController {
    // todo secure
    private final IssueService issueService;

    @GetMapping("/list")
    ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("projects1/listOfIssues");
        modelAndView.addObject("issues", issueService.getAll());
        return modelAndView;
    }
    public Iterable<Issue> getAll(){
        return issueService.getAll();
    }

    @GetMapping
    ModelAndView issueIndex() {
        ModelAndView modelAndView = new ModelAndView("projects1/issueIndex");

        modelAndView.addObject("issues", issueService.getAll());
        return modelAndView;
    }


    @GetMapping("{id}")
    public Issue getOne(@PathVariable Long id){
        return issueService.getOne(id);
    }

    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("projects1/addIssue");

        Issue issue = new Issue();
        modelAndView.addObject("issue", issue);
        return modelAndView;
    }

    @PostMapping("/save")
    String save(@ModelAttribute Issue issue) {

        boolean isNew = issue.getId() == null;
        boolean statusChanged = true;

        if (issue.getId() != null) {
            statusChanged = issue.getStatus() != issueService.getOne(issue.getId()).getStatus() || issue.getStatus() != null;
        }


        issueService.save(issue);

        if (isNew) {
            return "redirect:/issues";
        } else if (statusChanged) {
            return "redirect:/issues";
        } else {
            return "redirect:/issues/edit/" + issue.getId();
        }
    }


    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView= new ModelAndView("projects1/addIssue");
        Issue issue= issueService.getOne(id);
        modelAndView.addObject("issue",issue);
        return modelAndView;
    }
    @GetMapping("/changeIssueStatus/{id}")
    ModelAndView changeIssueStatus(@PathVariable Long id){
        ModelAndView modelAndView= new ModelAndView("projects1/changeIssueStatus");
        Issue issue= issueService.getOne(id);
        modelAndView.addObject("issue",issue);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    ModelAndView delete(@PathVariable Long id){
        ModelAndView modelAndView= new ModelAndView("projects1/delete");
        Issue issue= issueService.getOne(id);
        modelAndView.addObject("issue",issue);
        return modelAndView;
    }






    }
