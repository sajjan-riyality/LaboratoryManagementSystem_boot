package com.lab.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BillingDTO {
    private Long id;
    private Long patientId;
    private Double totalAmount;
    private Double discount;
    private Double netAmount;
    private String paymentStatus;
    private LocalDate billingDate;
}
