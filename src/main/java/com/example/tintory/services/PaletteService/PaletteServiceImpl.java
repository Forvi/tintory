package com.example.tintory.services.PaletteService;

import com.example.tintory.dto.ColorDto.ColorDto;
import com.example.tintory.dto.ColorDto.ColorMapper;
import com.example.tintory.dto.PaletteDtos.PaletteMapper;
import com.example.tintory.dto.PaletteDtos.PaletteResponseDto;
import com.example.tintory.entities.Color;
import com.example.tintory.entities.PaletteEntity;
import com.example.tintory.entities.UserEntity;
import com.example.tintory.exceptions.NotFoundException;
import com.example.tintory.repository.PaletteRepo;
import com.example.tintory.repository.UserRepo;
import com.example.tintory.services.PaletteService.interfaces.PaletteService;
import com.example.tintory.utils.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaletteServiceImpl implements PaletteService {

    private final PaletteRepo paletteRepo;
    private final UserRepo userRepo;
    private final PaletteMapper paletteMapper;
    private final ColorMapper colorMapper;

    public PaletteResponseDto createPalette(String name, List<ColorDto> colorsDto, UUID userId) {
        try {
            log.debug("Creating new palette: {}...", name);

            Validation.validateNull(name, colorsDto, userId);
            log.info("User data is valid");

            log.debug("Trying to find user...");
            UserEntity userEntity = userRepo
                    .findById(userId)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            log.info("User was successfully found: {}", userEntity.getName());

            List<Color> colors = colorsDto.stream()
                    .map(colorMapper::toModel)
                    .toList();

            PaletteEntity paletteEntity = PaletteEntity.builder()
                    .name(name)
                    .colors(colors)
                    .user(userEntity)
                    .build();

            PaletteEntity paletteSaved = paletteRepo.save(paletteEntity);
            log.info("Created palette: {}", name);

            return paletteMapper.toResponseDto(paletteSaved);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DeletePalette(UUID id) {
        try {
            log.debug("Trying to delete palette with id: {}", id);
            paletteRepo.deleteById(id);
            log.info("Palette with id {} was successfully deleted", id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PaletteResponseDto> getAllPalettes() {
        try {
            log.debug("Trying to find all palettes");
            return paletteRepo
                    .findAll()
                    .stream()
                    .map(paletteMapper::toResponseDto)
                    .toList();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PaletteResponseDto getPaletteById(UUID id) {
        try {
            PaletteEntity paletteEntity = paletteRepo
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("Palette not found"));

            return paletteMapper.toResponseDto(paletteEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
