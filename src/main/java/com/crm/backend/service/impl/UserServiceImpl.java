package com.crm.backend.service.impl;

import com.crm.backend.dto.UserCreateRequest;
import com.crm.backend.dto.UserResponse;
import com.crm.backend.dto.UserUpdateRequest;
import com.crm.backend.entity.User;
import com.crm.backend.exception.DuplicateEmailException;
import com.crm.backend.exception.UserNotFoundException;
import com.crm.backend.mapper.UserMapper;
import com.crm.backend.repository.UserRepository;
import com.crm.backend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        String email = request.getEmail().toLowerCase().trim();
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email already exists");
        }
        String hash = passwordEncoder.encode(request.getPassword());
        User user = UserMapper.toEntity(request, hash);
        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse updateUser(UUID id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        if (request.getEmail() != null) {
            String newEmail = request.getEmail().toLowerCase().trim();
            if (!newEmail.equals(user.getEmail()) && userRepository.existsByEmail(newEmail)) {
                throw new DuplicateEmailException("Email already exists");
            }
        }
        String hash = null;
        if (request.getPassword() != null) {
            hash = passwordEncoder.encode(request.getPassword());
        }
        UserMapper.updateEntity(user, request, hash);
        User saved = userRepository.save(user);
        return UserMapper.toResponse(saved);
    }

    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
