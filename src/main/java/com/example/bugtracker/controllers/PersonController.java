package com.example.bugtracker.controllers;

import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;


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

}