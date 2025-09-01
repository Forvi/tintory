package com.example.tintory.services.AuthService;

import com.example.tintory.dto.JwtToken.JwtRequestDto;
import com.example.tintory.dto.JwtToken.JwtResponseDto;
import com.example.tintory.dto.UserDtos.RegisteredUserDto;
import com.example.tintory.dto.UserDtos.RegistrationUserDto;
import com.example.tintory.dto.UserDtos.UserMapper;
import com.example.tintory.dto.UserDtos.UserResponseDto;
import com.example.tintory.exceptions.InvalidRegistrationData;
import com.example.tintory.services.AuthService.interfaces.AuthService;
import com.example.tintory.services.UserServices.UserServiceImpl;
import com.example.tintory.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponseDto createAuthToken(JwtRequestDto request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect login or password");
        }

        UserDetails userDetails = userService.loadUserByUsername(request.email());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponseDto(token);
    }

    @Override
    public RegisteredUserDto registerUser(RegistrationUserDto request) {
        if (!request.password().equals(request.confirmPassword()))
            throw new InvalidRegistrationData("Invalid password");

        if (userService.getUserByName(request.name()).isPresent())
            throw new InvalidRegistrationData("A user with this name already exists");

        try {
            UserResponseDto responseDto = userService.createUser(request.name(), request.email(), request.password());
            return userMapper.fromResponseToRegistered(responseDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
