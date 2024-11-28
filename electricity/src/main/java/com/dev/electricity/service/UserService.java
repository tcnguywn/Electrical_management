package com.dev.electricity.service;

import com.dev.electricity.dto.request.User.UserCreationRequest;
import com.dev.electricity.dto.request.User.UserUpdateRequest;
import com.dev.electricity.dto.response.UserResponse;
import com.dev.electricity.entity.User;
import com.dev.electricity.enums.Role;
import com.dev.electricity.mapper.UserMapper;
import com.dev.electricity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    private final UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsUserByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists");

        long years = ChronoUnit.YEARS.between(request.getDob(), LocalDate.now());
//        System.out.println(years);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ELECTRICIAN')")
    public List<UserResponse> getAllUsers() {
        log.info("Get all users");
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }
    @PreAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toUserResponse(user);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));

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
