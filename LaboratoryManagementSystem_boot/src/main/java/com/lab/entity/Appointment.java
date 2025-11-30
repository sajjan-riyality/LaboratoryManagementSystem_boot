package com.lab.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Patient is required")
    private Long patientId; // link to User with ROLE_PATIENT

    @NotNull(message = "Staff is required")
    private Long staffId; // link to User with ROLE_STAFF

    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;

    @NotNull(message = "Status is required")
    @Size(max = 20)
    private String status; // Scheduled, Completed, Cancelled

    @Size(max = 500)
    private String remarks;
}
