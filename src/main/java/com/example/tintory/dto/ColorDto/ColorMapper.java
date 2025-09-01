package com.example.tintory.dto.ColorDto;

import com.example.tintory.entities.Color;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    Color toModel(ColorDto dto);

    ColorDto toDto(Color model);

}
