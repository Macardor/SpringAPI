package com.codecool.SpringAPI.exception;

public class ActorNotFoundException extends RuntimeException {
    public ActorNotFoundException(Long id) {
        super("Could not find actor " + id);
    }
}
