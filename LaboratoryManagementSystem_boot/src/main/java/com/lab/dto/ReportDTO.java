package com.lab.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportDTO {
    private Long id;

    // link by id only in DTO
    private Long patientId;
    private Long testId;

    private String results;
    private String remarks;
    private LocalDate reportDate;
    private String filePath;
}
