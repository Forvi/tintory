package com.example.tintory.dto.UserDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record RegisteredUserDto(

        UUID id,

        @NotBlank
        @NotNull
        @Size(min = 2, max = 48)
        String name,

        @NotBlank
        @NotNull
        @Size(min = 2, max = 48)
        String email,

        @NotBlank
        @NotNull
        @Size(min = 6, max = 48)
        String password

) { }
