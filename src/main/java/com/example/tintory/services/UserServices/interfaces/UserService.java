package com.example.tintory.services.UserServices.interfaces;

import com.example.tintory.dto.PaletteDtos.CreatePaletteDto;
import com.example.tintory.dto.UserDtos.UserDto;
import com.example.tintory.dto.UserDtos.UserResponseDto;
import com.example.tintory.entities.PaletteEntity;
import com.example.tintory.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(String name, String email, String password);

    void DeleteUser(UUID id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserById(UUID id);

    Optional<UserEntity> getUserByName(String username);

}
