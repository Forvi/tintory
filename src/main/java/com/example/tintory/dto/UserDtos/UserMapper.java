package com.example.tintory.dto.UserDtos;

import com.example.tintory.dto.PaletteDtos.PaletteMapper;
import com.example.tintory.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PaletteMapper.class)
public interface UserMapper {

    @Mapping(target = "palettes", source = "palettes")
    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto dto);

    @Mapping(target = "palettes", source = "palettes")
    UserResponseDto toResponseDto(UserEntity entity);

    UserDto fromResponseToDto(UserResponseDto responseDto);

    RegisteredUserDto fromResponseToRegistered(UserResponseDto responseDto);

}
