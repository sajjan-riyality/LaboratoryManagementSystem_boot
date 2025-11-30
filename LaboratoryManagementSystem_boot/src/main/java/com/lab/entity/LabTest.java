package com.lab.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lab_tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Test name is required")
    @Size(max = 100)
    private String testName;

    @Size(max = 500)
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private double price;

    @NotNull(message = "Category is required")
    @Size(max = 50)
    private String category; 

    @NotNull(message = "Status is required")
    @Size(max = 20)
    private String status; // Active, Inactive
}
