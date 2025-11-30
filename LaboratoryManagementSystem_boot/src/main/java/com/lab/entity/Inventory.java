package com.lab.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Item name is required")
    @Size(max = 100)
    private String itemName;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotNull(message = "Unit is required")
    @Size(max = 20)
    private String unit; // e.g., ml, mg, pieces

    @NotNull(message = "Category is required")
    @Size(max = 50)
    private String category; // Chemicals, Test Kits, Equipment

    private LocalDate expiryDate;

    @Positive(message = "Low stock alert must be positive")
    private Integer lowStockAlert; // threshold (e.g., 10)
}
