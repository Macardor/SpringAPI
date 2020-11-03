package com.codecool.SpringAPI.exception;

public class DirectorNotFoundException extends RuntimeException{
    public DirectorNotFoundException(Long id) {
        super("Could not find director with id: " + id);
    }
}
