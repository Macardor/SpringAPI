package com.codecool.SpringAPI.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long id) {
        super("Could not find movie " + id);
    }
}