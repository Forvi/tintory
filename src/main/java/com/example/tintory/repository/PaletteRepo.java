package com.example.tintory.repository;

import com.example.tintory.entities.PaletteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaletteRepo extends JpaRepository<PaletteEntity, UUID> {

    PaletteEntity findByName(String name);

}
