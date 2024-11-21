package com.dev.electricity.mapper;

import com.dev.electricity.dto.request.RoleCreationRequest;
import com.dev.electricity.dto.response.RoleResponse;
import com.dev.electricity.entity.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toRole(RoleCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Role role = new Role();

        role.setRoleName( request.getRoleName() );
        role.setDescription( request.getDescription() );

        return role;
    }

    @Override
    public RoleResponse toRoleResponse(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleResponse.RoleResponseBuilder roleResponse = RoleResponse.builder();

        roleResponse.roleName( role.getRoleName() );
        roleResponse.description( role.getDescription() );

        return roleResponse.build();
    }
}
