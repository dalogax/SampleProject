package com.dalogax.sample.web.controller;

import java.util.List;

import com.dalogax.sample.web.dto.UserDataDto;
import com.dalogax.sample.web.dto.UserDto;
import com.dalogax.sample.model.service.UserService;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;

@RestController
@RequestMapping("/user")
@Api(value="user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET,
    produces = "application/json")
    @ApiOperation(value = "List all the users", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<UserDto> getAll() {
        return Lists.newArrayList(userService.getAllUsers());
    }

    @ApiOperation(value = "Gets the information of an user", response = UserDto.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
    produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "The user with the given id was not found")
    })
    public ResponseEntity<?> getById(@ApiParam(value = "The id of the user you want to get", required = true) @PathVariable("id") int id) {
        try {
            UserDto user = userService.getUser(id);
            return ResponseEntity.ok(user);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
    }

    @ApiOperation(value = "Creates a new user and returns it", response = UserDto.class)
    @RequestMapping(value = "/", method = RequestMethod.POST,
    consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created user")
    })
    public UserDto create(@ApiParam(name = "User data", value = "Data of the user you want to create", required = true) @RequestBody UserDataDto userData) {
        return userService.createUser(userData);
    }

    @ApiOperation(value = "Updates the info of an existing user", response = UserDto.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
    consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated user"),
            @ApiResponse(code = 404, message = "The resource you were trying to update is not found")
    })
    public ResponseEntity<?> update(@ApiParam(value = "The id of the user you want to update", required = true) @PathVariable("id") int id,
                                    @ApiParam(name = "User data", value = "Data of the user you want to update", required = true) @RequestBody UserDataDto userData){
        try {
            userService.updateUser(id, userData);
            return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
    }
}