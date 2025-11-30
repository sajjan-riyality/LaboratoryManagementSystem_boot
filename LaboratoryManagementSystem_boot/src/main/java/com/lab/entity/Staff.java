package com.lab.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @Size(max = 100)
    private String name;

    @NotNull(message = "Role is required")
    @Size(max = 50)
    private String role; // e.g., Technician, Lab Assistant, Admin

    @Email(message = "Invalid email")
    private String email;

    @Size(max = 15)
    private String phone;

    @NotNull(message = "Joining date is required")
    private LocalDate joiningDate;

    private LocalDate leavingDate;

    @NotNull(message = "Status is required")
    @Size(max = 20)
    private String status; // Active / Inactive
}
