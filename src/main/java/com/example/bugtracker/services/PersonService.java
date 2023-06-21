package com.example.bugtracker.services;


import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.repositories.AuthorityRepository;
import com.example.bugtracker.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Service
@Data


public class PersonService {

    private final AuthorityRepository authorityRepository;
    private final PersonRepository personRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonService(AuthorityRepository authorityRepository,
                         PersonRepository personRepository,
                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorityRepository = authorityRepository;
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void prepareAdminUser() {
        if (personRepository.findByName("admin").isPresent()) {
            return;
        }


        Person person = new Person("admin2","1234",null,"adminName",true,new Date());

        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));

        List<Authority> authorities = (List<Authority>) authorityRepository.findAll();
        person.setAuthorities(new HashSet<>(authorities));

        personRepository.save(person);
    }

    protected void savePerson(Person person) {
        String hashedPassword = bCryptPasswordEncoder.encode(person.getPassword());
        person.setPassword(hashedPassword);

        personRepository.save(person);
    }



    public Iterable<Person> getAll(){
        return personRepository.findAll();
    }

    public Person save(Person person){ return personRepository.save(person);}

    public Person getOne(@PathVariable Long id){
        Person person= personRepository.findById(id).orElse(null);
        if(person.isEnabled()){
            return person;
        } else {
            return null;
        }

    }




}
