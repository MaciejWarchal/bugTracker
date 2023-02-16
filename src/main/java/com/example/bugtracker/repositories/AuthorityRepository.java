package com.example.bugtracker.repositories;

import com.example.bugtracker.models.Authority.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface AuthorityRepository extends CrudRepository<Authority,Long> {
}
