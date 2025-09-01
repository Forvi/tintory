package com.example.tintory.exceptions;

public class InvalidRegistrationData extends RuntimeException {
    public InvalidRegistrationData(String message) {
        super(message);
    }
}
