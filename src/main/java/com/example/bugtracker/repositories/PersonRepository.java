package com.example.bugtracker.repositories;

import com.example.bugtracker.models.Person.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
}
