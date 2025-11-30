package com.lab.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class StaffDTO {
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 50)
    private String role;

    @Email
    private String email;

    @Size(max = 15)
    private String phone;

    @NotNull
    private LocalDate joiningDate;

    private LocalDate leavingDate;

    @NotNull
    @Size(max = 20)
    private String status; // Active/Inactive
}
