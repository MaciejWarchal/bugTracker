package com.example.bugtracker.services;


import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor


public class PersonService {

    private final PersonRepository personRepository;

    public Iterable<Person> getAll(){
        return personRepository.findAll();
    }

    public Person save(Person person){ return personRepository.save(person);}


}
