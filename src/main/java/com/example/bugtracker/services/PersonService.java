package com.example.bugtracker.services;


import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import com.example.bugtracker.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
@AllArgsConstructor


public class PersonService {

    private final PersonRepository personRepository;

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
