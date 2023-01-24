package com.example.bugtracker.models.Person;

import com.example.bugtracker.models.Authority.Authority;

import java.util.Set;

public class Person {

    Long id;
    String login;
    String password;
    String userRealName;
    Set<Authority> authorities;

    public Person(Long id, PersonDto personDto) {
        this.id = id;
        this.login = personDto.login;
        this.password = personDto.password;
        this.userRealName = personDto.userRealName;
        this.authorities = personDto.authorities;
    }
}
