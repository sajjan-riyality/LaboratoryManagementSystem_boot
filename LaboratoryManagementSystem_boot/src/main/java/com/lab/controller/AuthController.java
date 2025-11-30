package com.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.dto.LoginRequest;
import com.lab.dto.LoginResponse;
import com.lab.dto.UserDTO;
import com.lab.service.UserService;

@RestController
//@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // -------------------------------
    // User Registration
    // -------------------------------
    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

    // -------------------------------
    // User Login
    // -------------------------------
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
