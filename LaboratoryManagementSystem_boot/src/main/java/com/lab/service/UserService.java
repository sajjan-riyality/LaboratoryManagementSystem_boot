package com.lab.service;

import com.lab.dto.LoginRequest;
import com.lab.dto.LoginResponse;
import com.lab.dto.UserDTO;

public interface UserService {

    UserDTO register(UserDTO dto);

    LoginResponse login(LoginRequest request);

	String getRoleByUsername(String username);
}
