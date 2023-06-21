package com.example.bugtracker.controllers;

import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.repositories.AuthorityRepository;
import com.example.bugtracker.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final AuthorityRepository authorityRepository;


    @GetMapping
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("projects1/index");

        modelAndView.addObject("person", personService.getAll());
        return modelAndView;
    }
    @PostMapping("/personAdd")
    // person service czy POJO?
    public PersonService addUser(@ModelAttribute PersonService personService){
        return personService;
    }


    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("projects1/addPerson");
        Person person= new Person();
        modelAndView.addObject(person);
        return modelAndView;
    }

    @PostMapping("/save")
    String save(@ModelAttribute Person person) {

        boolean isNew = person.getId() == null;

        personService.save(person);

        if (isNew) {
            return "redirect:/projects";
        } else {
            return "redirect:/projects/edit/" + person.getId();
        }
    }

    @GetMapping("/authorities")
    public Iterable<Authority> getAuthorities(@RequestParam String username) {
        return authorityRepository.findAllByUsername(username);
    }

}