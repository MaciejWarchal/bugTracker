package com.example.bugtracker.repositories;

import com.example.bugtracker.enums.AuthorityType;
import com.example.bugtracker.models.Authority.Authority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Optional<Authority> findByAuthority(String authority);

    Optional<Authority> findByAuthority(AuthorityType authorityType);

    @Query("select a from Person p join p.authorities a where p.name = :name")
    Iterable<Authority> findAllByUsername(String name);
}
