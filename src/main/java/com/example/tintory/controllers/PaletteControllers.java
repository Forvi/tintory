package com.example.tintory.controllers;

import com.example.tintory.dto.PaletteDtos.CreatePaletteDto;
import com.example.tintory.dto.PaletteDtos.PaletteResponseDto;
import com.example.tintory.dto.UserDtos.UserResponseDto;
import com.example.tintory.services.PaletteService.PaletteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/palette")
public class PaletteControllers {

    private final PaletteServiceImpl paletteService;

    @PostMapping("/create")
    @Operation(summary = "Создать палитру")
    public ResponseEntity<PaletteResponseDto> createPalette(@RequestBody CreatePaletteDto request) {
        var result = paletteService.createPalette(
                request.name(),
                request.colors(),
                request.id()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/getall")
    @Operation(summary = "Получить список со всеми палитрами")
    public ResponseEntity<List<PaletteResponseDto>> getAllUsers() {
        var result = paletteService.getAllPalettes();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Получить палитру по ID")
    public ResponseEntity<PaletteResponseDto> getUserById(@RequestParam UUID id) {
        var result = paletteService.getPaletteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить палитру по ID")
    public ResponseEntity<Void> deleteUserById(@RequestParam UUID id) {
        paletteService.DeletePalette(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
