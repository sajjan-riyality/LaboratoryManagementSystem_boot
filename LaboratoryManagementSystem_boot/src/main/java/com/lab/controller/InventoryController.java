package com.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lab.dto.InventoryDTO;
import com.lab.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Only ADMIN can add inventory items
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<InventoryDTO> add(@Validated @RequestBody InventoryDTO dto) {
        InventoryDTO createdItem = inventoryService.addItem(dto);
        return ResponseEntity.ok(createdItem);
    }

    // Only ADMIN can update inventory items
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> update(@PathVariable Long id, @Validated @RequestBody InventoryDTO dto) {
        InventoryDTO updatedItem = inventoryService.updateItem(id, dto);
        return ResponseEntity.ok(updatedItem);
    }

    // Only ADMIN can delete inventory items
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.ok("Inventory item deleted!");
    }

    // ADMIN and STAFF can view inventory by ID
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> get(@PathVariable Long id) {
        InventoryDTO item = inventoryService.getItem(id);
        return ResponseEntity.ok(item);
    }

    // ADMIN and STAFF can view all items
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAll() {
        List<InventoryDTO> items = inventoryService.getAllItems();
        return ResponseEntity.ok(items);
    }

    // ADMIN and STAFF can filter items by category
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/category/{category}")
    public ResponseEntity<List<InventoryDTO>> getByCategory(@PathVariable String category) {
        List<InventoryDTO> items = inventoryService.getByCategory(category);
        return ResponseEntity.ok(items);
    }

    // ADMIN and STAFF can view low-stock items
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryDTO>> getLowStock() {
        List<InventoryDTO> items = inventoryService.getLowStockItems();
        return ResponseEntity.ok(items);
    }
}
