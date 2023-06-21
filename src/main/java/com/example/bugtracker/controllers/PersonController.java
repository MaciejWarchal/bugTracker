package com.example.bugtracker.controllers;

import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.repositories.AuthorityRepository;
import com.example.bugtracker.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import static com.example.bugtracker.enums.AuthorityType.ROLE_CREATE_USER;

@Controller
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/account")
    ModelAndView account() {
        ModelAndView modelAndView = new ModelAndView("projects1/myAccount");

        // Pobierz autentykację (zalogowanego użytkownika)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentname = null;
        if (authentication != null && authentication.isAuthenticated()) {
            // Sprawdź, czy autentykacja zawiera szczegóły użytkownika
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                currentname = userDetails.getUsername();
            }
        }

        modelAndView.addObject("currentPerson", personService.getByName(currentname));

        return modelAndView;
    }



    @PostMapping("/personAdd")
    // person service czy POJO?
    public PersonService addUser(@ModelAttribute PersonService personService){
        return personService;
    }


    @GetMapping("/create")
    @Secured("ROLE_CREATE_USER")

    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("projects1/addPerson");
        Person person= new Person();
        modelAndView.addObject(person);
        return modelAndView;
    }

    @PostMapping("/save")
    String save(@ModelAttribute Person person) {

        boolean isNew = person.getId() == null;
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));

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