package com.dev.electricity.controller;


import com.dev.electricity.dto.request.ApiResponse;
import com.dev.electricity.dto.request.RoleCreationRequest;
import com.dev.electricity.dto.response.RoleResponse;
import com.dev.electricity.repository.RoleRepository;
import com.dev.electricity.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleRepository roleRepository;
    private final RoleService roleService;

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleCreationRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(request))
                .build();
    }

    @DeleteMapping("/{roleName}")
    ApiResponse<Void> deleteRole(@PathVariable("roleName") String roleName) {
        roleService.deleteRole(roleName);
        return ApiResponse.<Void>builder().build();
    }
}
