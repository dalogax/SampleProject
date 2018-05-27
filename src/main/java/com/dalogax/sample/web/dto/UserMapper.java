package com.dalogax.sample.web.dto;

import com.dalogax.sample.model.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
    User userDataDtoToUser(UserDataDto userDataDto);
    UserDto userDataDtoToUserDto(UserDataDto userDataDto);

    default UserDto idAndUserDataDtoToUserDto(int id, UserDataDto userDataDto){
        return UserDto.builder()
                .id(id)
                .name(userDataDto.getName())
                .email(userDataDto.getEmail())
                .build();
    }

}