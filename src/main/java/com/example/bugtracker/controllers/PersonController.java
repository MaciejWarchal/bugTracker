package com.example.bugtracker.controllers;

import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.repositories.AuthorityRepository;
import com.example.bugtracker.services.AuthorityService;
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

import java.security.InvalidParameterException;
import java.util.Optional;

import static com.example.bugtracker.enums.AuthorityType.ROLE_CREATE_USER;

@Controller
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final AuthorityService authorityService;
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



    @GetMapping("/authoritiesSet")
    @Secured("ROLE_CREATE_USER")
    ModelAndView authoritiesSet() {
        ModelAndView modelAndView = new ModelAndView("projects1/addAuthority");




        modelAndView.addObject("people", personService.getAll());
        modelAndView.addObject("username", new String());
        modelAndView.addObject("authority1", new String());


        return modelAndView;
    }
    /*@GetMapping("/authorities")
    @Secured("ROLE_CREATE_USER")
    public Iterable<Authority> getAuthorities(@RequestParam String username) {
        return authorityService.getAllByUsername(username);
    }*/

    @PostMapping("/authorities{username}")
    //@Secured("ROLE_CREATE_USER")
    public String addAuthority(@PathVariable String username, @RequestParam String authority) {
        // 1. Wyszukujemy użytkownika po username

        Optional<Person> optionalPerson = Optional.ofNullable(personService.getByName(username));

        if (optionalPerson.isEmpty()) {
            throw new InvalidParameterException("Nie znaleźliśmy użytkownika");
        }

        // 2. Wyszykujemy uprawnienie po authority

        Optional<Authority> optionalAuthorityInstance = Optional.ofNullable(authorityService.getAuthorityByAuthority(authority));
        if (optionalAuthorityInstance.isEmpty()) {
            throw new InvalidParameterException("Nie znaleźliśmy uprawnienia!");
        }

        // 3. Dodajemy to uprawnienie do listy uprawnień użytkownika
        Person person = optionalPerson.get();
        person.getAuthorities().add(optionalAuthorityInstance.get());

        // 4. Zapisujemy użytkownika
        personService.save(person);

        return "redirect:/person";

    }

}