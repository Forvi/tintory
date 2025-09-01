package com.example.tintory.dto.UserDtos;

import com.example.tintory.dto.PaletteDtos.UserInPaletteDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<UserInPaletteDto> palettes
) { }