package com.example.bugtracker.exceptions;

public class ResourceCouldNotBeCreated extends RuntimeException{

    public ResourceCouldNotBeCreated(){
        super("Resource could not be created");
    }
}
