package com.example.bugtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "projects1/index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "projects1/contact";
    }
}
