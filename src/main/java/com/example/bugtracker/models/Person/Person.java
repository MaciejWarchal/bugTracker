package com.example.bugtracker.models.Person;

import com.example.bugtracker.models.Authority.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(nullable = false, unique = true)

    private Long id;
    private String login;
    private String password;
    private String userRealName;
    private String name;
    private Boolean enabled=true;
    private Date dateCreated;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "person_authorities",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Set<Authority> authorities;

    public Person(String login, String password, String userRealName, String name, Boolean enabled, Date dateCreated) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.enabled = enabled;
        this.dateCreated = dateCreated;
    }



    public boolean isEnabled() {
        if(this.enabled==true){
            return true;
        } else {
            return false;
        }
    }

    /*private List<GrantedAuthority> getUserAuthorities(Person person) {
        Set<GrantedAuthority> grantedAuthorities;
        grantedAuthorities = new HashSet<>();
        for (Authority authority : person.authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.name.toString()));
        }
        return new ArrayList<>(grantedAuthorities);
    }*/
}
