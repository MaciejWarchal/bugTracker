package com.example.bugtracker.repositories;

import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long>, CrudRepository<Person,Long>, JpaSpecificationExecutor<Project> {

    Optional<Person> findByName(String name);

    Optional<Person> findByNameAndEnabled(String userRealName, Boolean enabled);

    Iterable<Person> findAllByName(String s1);

    Iterable<Person> findAllByDateCreatedAfter(Date d);
}
