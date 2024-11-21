package com.dev.electricity.mapper;

import com.dev.electricity.dto.request.RoleCreationRequest;
import com.dev.electricity.dto.response.RoleResponse;
import com.dev.electricity.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface RoleMapper {
    Role toRole(RoleCreationRequest request);
    RoleResponse toRoleResponse(Role role);
}
