package com.example.tintory.controllers;


import com.example.tintory.dto.JwtToken.JwtRequestDto;
import com.example.tintory.dto.JwtToken.JwtResponseDto;
import com.example.tintory.dto.UserDtos.RegistrationUserDto;
import com.example.tintory.exceptions.AppError;
import com.example.tintory.exceptions.InvalidRegistrationData;
import com.example.tintory.services.AuthService.AuthServiceImpl;
import com.example.tintory.services.UserServices.interfaces.UserService;
import com.example.tintory.utils.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/signin")
    @Operation(summary = "Авторизоваться")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDto request) {
        try {
            JwtResponseDto response = authService.createAuthToken(request);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(), e.getMessage()),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @PostMapping("/signup")
    @Operation(summary = "Зарегистрироваться")
    public ResponseEntity<?> registerNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        try {
            var result = authService.registerUser(registrationUserDto);
            return ResponseEntity.ok().body(result);
        } catch (InvalidRegistrationData e) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
