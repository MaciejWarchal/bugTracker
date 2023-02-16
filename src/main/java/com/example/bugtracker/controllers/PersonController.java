package com.example.bugtracker.controllers;

import com.example.bugtracker.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    // person service czy POJO?
    public PersonService addUser(@ModelAttribute PersonService personService){
        return personService;
    }
}