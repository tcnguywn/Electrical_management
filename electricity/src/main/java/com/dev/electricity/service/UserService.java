package com.dev.electricity.service;

import com.dev.electricity.dto.request.User.UserCreationRequest;
import com.dev.electricity.dto.request.User.UserUpdateRequest;
import com.dev.electricity.dto.response.UserResponse;
import com.dev.electricity.entity.User;
import com.dev.electricity.mapper.UserMapper;
import com.dev.electricity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.Hibernate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsUserByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists");
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUser(long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getUsersByFullname(String fullName) {
        List<User> users = userRepository.findByFullName(fullName);
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse updateUser(long idUser, UserUpdateRequest request) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUser(user,request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(long idUser) {
        userRepository.deleteById(idUser);
    }

}
