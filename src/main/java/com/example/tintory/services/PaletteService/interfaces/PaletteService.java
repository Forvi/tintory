package com.example.tintory.services.PaletteService.interfaces;

import com.example.tintory.dto.ColorDto.ColorDto;
import com.example.tintory.dto.PaletteDtos.PaletteResponseDto;
import com.example.tintory.dto.UserDtos.UserDto;
import com.example.tintory.dto.UserDtos.UserResponseDto;
import com.example.tintory.entities.Color;

import java.util.List;
import java.util.UUID;

public interface PaletteService {

    PaletteResponseDto createPalette(String name, List<ColorDto> colors, UUID userId);

    void DeletePalette(UUID id);

    List<PaletteResponseDto> getAllPalettes();

    PaletteResponseDto getPaletteById(UUID id);

}
