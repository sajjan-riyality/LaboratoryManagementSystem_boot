package com.lab.dto;

import lombok.Data;

@Data
public class PatientDTO {
    private Long id;
    private String fullName;
    private int age;
    private String gender;
    private String phone;
    private String email;
    private String address;
}
