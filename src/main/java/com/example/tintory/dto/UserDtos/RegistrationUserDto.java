package com.example.tintory.dto.UserDtos;

public record RegistrationUserDto(
        String name,
        String password,
        String confirmPassword,
        String email
) { }
