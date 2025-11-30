package com.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lab.dto.StaffDTO;
import com.lab.service.StaffService;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // Only ADMIN can add staff
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<StaffDTO> add(@Validated @RequestBody StaffDTO dto) {
        StaffDTO createdStaff = staffService.addStaff(dto);
        return ResponseEntity.ok(createdStaff);
    }

    // Only ADMIN can update staff
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> update(@PathVariable Long id, @Validated @RequestBody StaffDTO dto) {
        StaffDTO updatedStaff = staffService.updateStaff(id, dto);
        return ResponseEntity.ok(updatedStaff);
    }

    // Only ADMIN can delete staff
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Staff deleted successfully");
    }

    // ADMIN and STAFF can view staff by ID
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getById(@PathVariable Long id) {
        StaffDTO staff = staffService.getStaffById(id);
        return ResponseEntity.ok(staff);
    }

    // ADMIN and STAFF can view all staff
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAll() {
        List<StaffDTO> staffList = staffService.getAllStaff();
        return ResponseEntity.ok(staffList);
    }

   
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/role/{role}")
    public ResponseEntity<List<StaffDTO>> getByRole(@PathVariable String role) {
        List<StaffDTO> staffList = staffService.getByRole(role);
        return ResponseEntity.ok(staffList);
    }

    // ADMIN and STAFF can filter by status
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<StaffDTO>> getByStatus(@PathVariable String status) {
        List<StaffDTO> staffList = staffService.getByStatus(status);
        return ResponseEntity.ok(staffList);
    }
}
