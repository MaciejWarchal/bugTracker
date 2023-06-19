package com.example.bugtracker.controllers;

import com.example.bugtracker.filters.ProjectFilter;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.services.PersonService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Data

public class LoginController {

    private final PersonService personService;



    @GetMapping("/login")
    ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("projects1/index");

        modelAndView.addObject("people", personService.getAll());
        return modelAndView;
    }

    @GetMapping("{id}")
    public Person getOne(@PathVariable Long id){
        return personService.getOne(id);
    }

}
