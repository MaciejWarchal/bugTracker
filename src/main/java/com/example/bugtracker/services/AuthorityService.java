package com.example.bugtracker.services;

import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.repositories.AuthorityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorityService {
    AuthorityRepository authorityRepository;

    public Iterable<Authority> getAll(){
        return authorityRepository.findAll();
    }

    public Authority getAuthorityById(Long id){
        return authorityRepository.findById(id).orElse(null);
    }
    public Iterable<Authority> getAllByUsername(String name){
        return authorityRepository.findAllByUsername(name);
    }
    public Authority getAuthorityByAuthority(String authority){
        return authorityRepository.findByAuthority(authority).orElse(null);
    }

}
