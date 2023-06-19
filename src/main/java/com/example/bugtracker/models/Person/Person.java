package com.example.bugtracker.models.Person;

import com.example.bugtracker.models.Authority.Authority;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

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
    private Boolean enabled;
    private Date dateCreated;

    @ManyToMany
            @JoinTable(name = "person_authorites",
                        joinColumns = @JoinColumn(name = "person_id"),
                        inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Set<Authority> authorities;



    public boolean isEnabled() {
        if(this.enabled==true){
            return true;
        } else {
            return false;
        }
    }
}
