package com.example.bugtracker.models.Authority;

import com.example.bugtracker.enums.AuthorityType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Authority {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    AuthorityType authority;

    public Authority(AuthorityType authority) {
        this.authority = authority;
    }

    public Authority() {

    }

    public Long getId() {
        return id;
    }

    public AuthorityType getAuthority() {
        return authority;
    }
}



