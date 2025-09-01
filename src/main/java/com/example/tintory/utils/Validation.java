package com.example.tintory.utils;

import java.util.Objects;

public class Validation {

    public static void validateNull(Object... args) {
        for (int i = 0; i < args.length; i++) {
            Objects.requireNonNull(args[i], String.format("Null object at index %d. Total args: %d", i, args.length));
        }
    }
    
}
