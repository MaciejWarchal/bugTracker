package com.example.bugtracker.models.Authority;

public class Authority {

    private Long id;
    private String name;

    public Authority(Long id, AuthorityDto authorityDto) {
        this.id = id;
        this.name = authorityDto.name;
    }
}
