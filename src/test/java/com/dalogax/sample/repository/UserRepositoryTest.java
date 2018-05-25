package com.dalogax.sample.repository;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;

import com.dalogax.sample.model.User;
import com.dalogax.sample.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javassist.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
    private UserRepository userRepository;
 
    @Test
    public void findAllUsers() {
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertTrue(!users.isEmpty());
    }
 
    @Test
    public void findUserById() throws NotFoundException {
        User user = userRepository.findUserById(1);
        assertNotNull(user);
    }
 
    @Test
    public void createUser() throws NotFoundException {
        User user = new User(0, "Peter", "peter@gmail.com");
        User savedUser = userRepository.create(user);
        User newUser = userRepository.findUserById(savedUser.getId());
        assertNotNull(newUser);
        assertEquals("Peter", newUser.getName());
        assertEquals("peter@gmail.com", newUser.getEmail());
    }

    @Test
    public void updateUser() throws NotFoundException {
        User user = new User(0, "Juan", "juan@gmail.com");
        User savedUser = userRepository.create(user);
        savedUser.setName("Peter Griffin");
        savedUser.setEmail("peter@outlook.com");

        userRepository.update(savedUser);

        User updatedUser = userRepository.findUserById(savedUser.getId());
        assertNotNull(updatedUser);
        assertEquals("Peter Griffin", updatedUser.getName());
        assertEquals("peter@outlook.com", updatedUser.getEmail());
    }

}
