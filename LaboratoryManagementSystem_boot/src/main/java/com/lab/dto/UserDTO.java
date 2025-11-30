package com.lab.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    @NotNull(message = "Username is required")
    @Size(max = 50)
    private String username;

    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Password is required")
    private String password;

    private Set<String> roles; // ROLE_ADMIN, ROLE_STAFF, ROLE_PATIENT
}
