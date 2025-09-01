package com.example.tintory.dto.PaletteDtos;

import com.example.tintory.entities.Color;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record UserInPaletteDto(

        @NotNull
        UUID id,

        @NotBlank
        @NotNull
        @Size(min = 2, max = 48)
        String name,

        @NotNull
        List<Color> colors

) { }
