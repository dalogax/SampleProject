package com.dalogax.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dalogax.sample.model.User;
import com.dalogax.sample.repository.UserRepository;
import com.google.common.collect.Lists;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api(value="user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "List all the users", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @ApiOperation(value = "Gets the information of an user", response = User.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved user"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public User getById(@ApiParam(value = "The id of the user you want to get", required = true) @PathVariable("id") int id) {
        return userRepository.findUserById(id);
    }

    //RestTemplate restTemplate = new RestTemplate();
    //URI uri = URI.create("http://localhost:8090/recommended");
    //restTemplate.getForObject(uri, String.class);
}