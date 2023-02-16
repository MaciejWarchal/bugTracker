package com.example.bugtracker.models.Person;

import com.example.bugtracker.models.Authority.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor

public class PersonDto {

    String login;
    String userRealName;
    Set<Authority> authorities;
}
