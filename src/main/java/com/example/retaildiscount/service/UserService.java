package com.example.retaildiscount.service;


import com.example.retaildiscount.dto.request.UserRequest;
import com.example.retaildiscount.dto.response.UserResponse;
import com.example.retaildiscount.mapper.UserMapper;
import com.example.retaildiscount.model.User;
import com.example.retaildiscount.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        return userMapper.map(userRepository.findAll());
    }

    public UserResponse getUserById(UUID id) {
        return userMapper.map(userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found")));
    }

    public UserResponse createUser(UserRequest user) {
        return userMapper.map(userRepository.save(userMapper.map(user)));
    }

    public UserResponse updateUser(UUID id, UserRequest request) {
        User existing = userRepository.findById(id).orElseThrow();
        existing.setName(request.name());
        existing.setUserType(request.userType());
        existing.setRegisteredDate(request.registeredDate());
        return userMapper.map(userRepository.save(existing));
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
