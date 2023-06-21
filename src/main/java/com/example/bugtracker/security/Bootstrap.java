package com.example.bugtracker.security;

import com.example.bugtracker.enums.AuthorityType;
import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.repositories.AuthorityRepository;
import com.example.bugtracker.services.PersonService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Bootstrap implements InitializingBean {

    private final AuthorityRepository authorityRepository;
    private final PersonService personService;

    public Bootstrap(AuthorityRepository authorityRepository,
                     PersonService personService) {
        this.authorityRepository = authorityRepository;
        this.personService = personService;
    }
    //

    @Override
    public void afterPropertiesSet() throws Exception {
        for (AuthorityType authorityType : AuthorityType.values()) {
            Optional<Authority> authority = authorityRepository.findByAuthority(authorityType);

            if (authority.isEmpty()) {
                Authority a = new Authority(AuthorityType.ROLE_CREATE_USER);
                authorityRepository.save(a);
            }
        }

        personService.prepareAdminUser();
    }
    }


