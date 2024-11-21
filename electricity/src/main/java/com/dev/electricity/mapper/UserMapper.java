package com.dev.electricity.mapper;


import com.dev.electricity.dto.request.User.UserCreationRequest;
import com.dev.electricity.dto.request.User.UserUpdateRequest;
import com.dev.electricity.dto.response.UserResponse;
import com.dev.electricity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    @Mapping(source = "usageHistories", target = "usageHistories")
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
