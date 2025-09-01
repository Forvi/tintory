package com.example.tintory.dto.PaletteDtos;

import com.example.tintory.dto.ColorDto.ColorDto;
import java.util.List;
import java.util.UUID;

public record CreatePaletteDto(
        String name,
        List<ColorDto> colors,
        UUID id
) { }
