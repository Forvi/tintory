package com.example.tintory.dto.PaletteDtos;

import com.example.tintory.entities.PaletteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaletteMapper {

    PaletteDto toDto(PaletteEntity entity);

    PaletteEntity toEntity(PaletteDto dto);

    default PaletteResponseDto toResponseDto(PaletteEntity entity) {
        return  new PaletteResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getColors(),
                entity.getUser().getId()
        );
    }

}
