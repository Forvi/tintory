package com.example.tintory.services.UserServices;

import com.example.tintory.dto.UserDtos.UserMapper;
import com.example.tintory.dto.UserDtos.UserResponseDto;
import com.example.tintory.entities.UserEntity;
import com.example.tintory.exceptions.NotFoundException;
import com.example.tintory.repository.UserRepo;
import com.example.tintory.services.UserServices.interfaces.UserService;
import com.example.tintory.utils.Validation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(String name, String email, String password) {
        try {
            log.debug("Creating new user: {}...", name);

            Validation.validateNull(name, email, password);
            log.info("User data is valid");

            UserEntity userEntity = UserEntity.builder()
                    .name(name)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .build();

            UserEntity savedUser = userRepo.save(userEntity);
            log.info("Created user: {}", name);

            return userMapper.toResponseDto(savedUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void DeleteUser(UUID id) {
        try {
            log.debug("Trying to delete user with id: {}", id);
            userRepo.deleteById(id);
            log.info("User with id {} was successfully deleted", id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        try {
            log.debug("Getting all users...");
            List<UserResponseDto> users = userRepo.findAll()
                    .stream()
                    .map(userMapper::toResponseDto)
                    .toList();

            log.info("{} users found", users.size());
            return users;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserResponseDto getUserById(UUID id) {
        try {
            UserEntity userEntity = userRepo
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));

            return userMapper.toResponseDto(userEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<UserEntity> getUserByName(String username) {
        try {
            return userRepo.findByName(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
