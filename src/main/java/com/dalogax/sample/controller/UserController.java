package com.dalogax.sample.controller;

import java.util.List;

import com.dalogax.sample.model.User;
import com.dalogax.sample.repository.UserRepository;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable("id") int id) {
        return userRepository.findUserById(id);
    }

    //RestTemplate restTemplate = new RestTemplate();
    //URI uri = URI.create("http://localhost:8090/recommended");
    //restTemplate.getForObject(uri, String.class);
}