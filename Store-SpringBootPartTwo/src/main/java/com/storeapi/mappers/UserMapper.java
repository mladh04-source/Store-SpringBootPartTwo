package com.storeapi.mappers;

import com.storeapi.dtos.RegisterUserRequest;
import com.storeapi.dtos.UpdateUserRequest;
import com.storeapi.dtos.UserDto;
import com.storeapi.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
