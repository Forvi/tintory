package com.example.tintory.controllers;

import com.example.tintory.dto.UserDtos.UserCreateDto;
import com.example.tintory.dto.UserDtos.UserResponseDto;
import com.example.tintory.services.UserServices.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(summary = "Создать пользователя")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto request) {
        var result = userService.createUser(
                request.name(),
                request.email(),
                request.password()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/getall")
    @Operation(summary = "Получить список со всеми пользователями")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        var result = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Получить пользователя по ID")
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam UUID id) {
        var result = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить пользователя по ID")
    public ResponseEntity<Void> deleteUserById(@RequestParam UUID id) {
        userService.DeleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
