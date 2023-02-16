package com.example.bugtracker.models.Person;

import com.example.bugtracker.models.Authority.Authority;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(nullable = false, unique = true)

    Long id;
    String login;
    String password;
    String userRealName;

    @ManyToMany
            @JoinTable(name = "person_authorites",
                        joinColumns = @JoinColumn(name = "person_id"),
                        inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Set<Authority> authorities;

    public Person(Long id,String password, PersonDto personDto) {
        this.id = id;
        this.login = personDto.login;
        this.password = password ;
        this.userRealName = personDto.userRealName;
        this.authorities = personDto.authorities;
    }

}
