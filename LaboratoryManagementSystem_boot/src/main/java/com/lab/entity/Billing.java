package com.lab.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Patient is required")
    private Long patientId; // link to User with ROLE_PATIENT

    @NotNull(message = "Total amount is required")
    @PositiveOrZero(message = "Total amount must be positive or zero")
    private Double totalAmount;

    @PositiveOrZero(message = "Discount must be positive or zero")
    private Double discount;

    @NotNull(message = "Net amount is required")
    @PositiveOrZero(message = "Net amount must be positive or zero")
    private Double netAmount;

    @NotNull(message = "Payment status is required")
    @Size(max = 20)
    private String paymentStatus;  // Paid, Pending, Failed

    @NotNull(message = "Billing date is required")
    private LocalDate billingDate;
}
