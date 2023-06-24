package com.example.bugtracker.repositories;

import com.example.bugtracker.enums.AuthorityType;
import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.models.Project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Long>, CrudRepository<Authority,Long>, JpaSpecificationExecutor<Authority> {

    Optional<Authority> findByAuthority(String authority);

    Optional<Authority> findByAuthority(AuthorityType authorityType);

    @Query("select a from Person p join p.authorities a where p.name = :name")
    Iterable<Authority> findAllByUsername(String name);

}
