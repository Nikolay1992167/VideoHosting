package ru.clevertec.dto.user;

public record UserResponse(Long id,
                           String nickname,
                           String name,
                           String email) {
}