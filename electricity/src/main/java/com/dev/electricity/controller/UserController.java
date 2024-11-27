package com.dev.electricity.controller;


import com.dev.electricity.dto.request.ApiResponse;
import com.dev.electricity.dto.request.User.UserCreationRequest;
import com.dev.electricity.dto.request.User.UserUpdateRequest;
import com.dev.electricity.dto.response.UserResponse;
import com.dev.electricity.entity.User;
import com.dev.electricity.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfor() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping("/fullname/{fullName}")
    ApiResponse<List<UserResponse>> getUserByUsername(@PathVariable String fullName){
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsersByFullname(fullName))
                .build();
    }

    @GetMapping("/{idUser}")
    ApiResponse<UserResponse> getUser(@PathVariable long idUser) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(idUser))
                .build();
    }

    @PutMapping("/{idUser}")
    ApiResponse<UserResponse> updateUser(@PathVariable long idUser, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(idUser, request))
                .build();
    }

    @DeleteMapping("/{idUser}")
    ApiResponse<String> deleteUser(@PathVariable long idUser) {
        userService.deleteUser(idUser);
        return ApiResponse.<String>builder()
                .result("User has been deleted")
                .build();
    }

}
