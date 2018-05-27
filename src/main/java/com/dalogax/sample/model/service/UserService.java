package com.dalogax.sample.model.service;

import java.util.List;
import java.util.stream.Collectors;

import com.dalogax.sample.model.repository.UserRepository;
import com.dalogax.sample.web.dto.UserDataDto;
import com.dalogax.sample.web.dto.UserDto;
import com.dalogax.sample.web.dto.UserMapper;

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
        UserDto userDto = UserMapper.INSTANCE.idAndUserDataDtoToUserDto(id,userDataDto);
        this.userRepository.update(UserMapper.INSTANCE.userDtoToUser(userDto));
    }

}