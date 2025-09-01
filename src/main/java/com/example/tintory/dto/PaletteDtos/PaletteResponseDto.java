package com.example.tintory.dto.PaletteDtos;

import com.example.tintory.entities.Color;

import java.util.List;
import java.util.UUID;

public record PaletteResponseDto(

        UUID id,

        String name,

        List<Color> color,

        UUID userId

) { }
