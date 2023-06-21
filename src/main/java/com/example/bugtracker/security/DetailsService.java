package com.example.bugtracker.security;


import com.example.bugtracker.models.Authority.Authority;
import com.example.bugtracker.models.Person.Person;
import com.example.bugtracker.repositories.PersonRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    public DetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        // Pobieramy użytkownika z bazy

        Optional<Person> person = personRepository.findByName(userName);

        if (person.isEmpty()) {
            throw new UsernameNotFoundException(userName);
        }

        Person existingPerson = person.get();

        List<GrantedAuthority> authorities = findUserAuthorities(existingPerson);

        // Wysyłamy go do Spring Security w odpowiedniej formie

        return new User(existingPerson.getName(),
                existingPerson.getPassword(),
                authorities);
    }

    private List<GrantedAuthority> findUserAuthorities(Person person) {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Authority authority : person.getAuthorities()) {
            String authName = authority.getAuthority().name();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authName);
            grantedAuthorities.add(grantedAuthority);
        }

        return new ArrayList<>(grantedAuthorities);
    }

}
