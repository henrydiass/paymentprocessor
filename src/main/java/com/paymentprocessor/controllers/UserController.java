package com.paymentprocessor.controllers;

import com.paymentprocessor.domain.entities.user.User;
import com.paymentprocessor.dtos.UserDTO;
import com.paymentprocessor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO payload) {
        User newUser = userService.createUser(payload);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<User> retrieveUsers() {
        return userService.getUsers();
    }
}
