package com.dalogax.sample.service;

import java.util.List;
import java.util.stream.Collectors;

import com.dalogax.sample.dto.UserDataDto;
import com.dalogax.sample.dto.UserDto;
import com.dalogax.sample.mapper.UserMapper;
import com.dalogax.sample.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers(){
        return this.userRepository.findAll().stream()
                                  .map(user -> UserMapper.INSTANCE.userToUserDto(user))
                                  .collect(Collectors.toList());

    }

    public UserDto getUser(int id) throws NotFoundException{
        return UserMapper.INSTANCE.userToUserDto(this.userRepository.findUserById(id));

    }

    public UserDto createUser(UserDataDto userDataDto){
        return UserMapper.INSTANCE.userToUserDto(
            this.userRepository.create(UserMapper.INSTANCE.userDataDtoToUser(userDataDto)));
    }

    public void updateUser(int id, UserDataDto userDataDto) throws NotFoundException{
        UserDto userDto = UserMapper.INSTANCE.userDataDtoToUserDto(userDataDto);
        userDto.setId(id);
        this.userRepository.update(UserMapper.INSTANCE.userDtoToUser(userDto));
    }

}