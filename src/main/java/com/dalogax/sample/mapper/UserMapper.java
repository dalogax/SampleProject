package com.dalogax.sample.mapper;

import com.dalogax.sample.dto.UserDto;
import com.dalogax.sample.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}