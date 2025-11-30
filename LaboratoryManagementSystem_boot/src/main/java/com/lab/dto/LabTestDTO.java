package com.lab.dto;

import lombok.Data;

@Data
public class LabTestDTO {
    private Long id;
    private String testName;
    private String description;
    private double price;
    private String category;
}
