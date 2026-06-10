package com.crm.backend.service;

import com.crm.backend.dto.UserCreateRequest;
import com.crm.backend.dto.UserResponse;
import com.crm.backend.dto.UserUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse createUser(UserCreateRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(UUID id);
    UserResponse updateUser(UUID id, UserUpdateRequest request);
    void deleteUser(UUID id);
}
