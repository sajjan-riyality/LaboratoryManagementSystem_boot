package com.lab.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class InventoryDTO {
    private Long id;
    private String itemName;
    private Integer quantity;
    private String unit;
    private String category;
    private LocalDate expiryDate;
    private Integer lowStockAlert;
}
