package com.lab.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDTO {

    private Long id;
    private Long patientId;
    private Long staffId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;
    private String remarks;
}
