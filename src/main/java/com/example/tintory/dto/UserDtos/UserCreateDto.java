package com.example.tintory.dto.UserDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDto(

        @NotBlank
        @Size(min = 2, max = 48)
        String name,

        @NotBlank
        @Size(min = 2, max = 48)
        String email,

        @NotBlank
        @Size(min = 6, max = 48)
        String password
) { }
