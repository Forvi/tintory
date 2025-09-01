package com.example.tintory.dto.ColorDto;

import jakarta.validation.constraints.NotNull;

public record ColorDto(

        @NotNull
        String hexCode

) { }
