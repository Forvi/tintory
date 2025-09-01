package com.example.tintory.dto.JwtToken;

public record JwtRequestDto(
    String email,
    String password
) { }
