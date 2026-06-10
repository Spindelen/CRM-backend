package com.crm.backend.mapper;

import com.crm.backend.dto.UserCreateRequest;
import com.crm.backend.dto.UserResponse;
import com.crm.backend.dto.UserUpdateRequest;
import com.crm.backend.entity.Role;
import com.crm.backend.entity.User;

public final class UserMapper {

    private UserMapper() {}

    public static UserResponse toResponse(User user) {
        if (user == null) return null;
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static User toEntity(UserCreateRequest req, String passwordHash) {
        if (req == null) return null;
        Role role = null;
        if (req.getRole() != null) {
            try {
                role = Role.valueOf(req.getRole().toUpperCase());
            } catch (Exception ignored) {}
        }
        return User.builder()
                .email(req.getEmail().toLowerCase().trim())
                .passwordHash(passwordHash)
                .role(role != null ? role : Role.SALES)
                .build();
    }

    public static void updateEntity(User user, UserUpdateRequest req, String passwordHash) {
        if (user == null || req == null) return;
        if (req.getEmail() != null) user.setEmail(req.getEmail().toLowerCase().trim());
        if (passwordHash != null) user.setPasswordHash(passwordHash);
        if (req.getRole() != null) {
            try {
                user.setRole(Role.valueOf(req.getRole().toUpperCase()));
            } catch (Exception ignored) {}
        }
    }
}
