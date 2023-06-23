package com.example.bugtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class IndexController {

   // @GetMapping("/")
   // public String index() {
    //    return "projects1/index";
    ///}

    @GetMapping
    public String contact() {
        return "projects1/contact";
    }
}
