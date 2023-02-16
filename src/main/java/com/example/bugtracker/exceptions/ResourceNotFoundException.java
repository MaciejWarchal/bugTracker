package com.example.bugtracker.exceptions;

public class ResourceNotFoundException extends RuntimeException{


    public ResourceNotFoundException(){
        super("NotFound");
    }
}
