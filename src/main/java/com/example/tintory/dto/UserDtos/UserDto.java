package com.example.tintory.dto.UserDtos;

import com.example.tintory.dto.PaletteDtos.PaletteDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

public record UserDto(

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
        String password,

        @NotNull
        List<PaletteDto> palettes

) { }
