package com.lab.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Full name is required")
    @Size(max = 100)
    private String fullName;

    @Positive(message = "Age must be positive")
    private int age;

    @NotNull(message = "Gender is required")
    @Size(max = 10)
    private String gender;

    @NotNull(message = "Phone is required")
    @Size(max = 15)
    private String phone;

    @Email(message = "Invalid email")
    private String email;

    @Size(max = 250)
    private String address;
}
