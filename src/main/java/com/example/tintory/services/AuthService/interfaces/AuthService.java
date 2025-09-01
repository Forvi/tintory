package com.example.tintory.services.AuthService.interfaces;

import com.example.tintory.dto.JwtToken.JwtRequestDto;
import com.example.tintory.dto.JwtToken.JwtResponseDto;
import com.example.tintory.dto.UserDtos.RegisteredUserDto;
import com.example.tintory.dto.UserDtos.RegistrationUserDto;

public interface AuthService {

    JwtResponseDto createAuthToken(JwtRequestDto request);
    RegisteredUserDto registerUser(RegistrationUserDto request);

}
