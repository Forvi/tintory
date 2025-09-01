package com.example.tintory.repository;

import com.example.tintory.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);

}
