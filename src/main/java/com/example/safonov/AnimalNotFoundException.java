package com.example.safonov;

public class AnimalNotFoundException extends Exception {
    public AnimalNotFoundException(long id) {
        super(String.format("Animal with id %s not found!", id));
    }
}

