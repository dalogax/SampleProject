package com.dalogax.sample.service;

import java.util.List;
import java.util.stream.Collectors;

import com.dalogax.sample.dto.UserDto;
import com.dalogax.sample.mapper.UserMapper;
import com.dalogax.sample.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers(){
        return this.userRepository.findAll().stream()
                                  .map(user -> UserMapper.INSTANCE.userToUserDto(user))
                                  .collect(Collectors.toList());

    }

}