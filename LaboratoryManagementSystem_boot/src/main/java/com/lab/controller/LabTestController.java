package com.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lab.dto.LabTestDTO;
import com.lab.service.LabTestService;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "*")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    // Only ADMIN can add tests
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<LabTestDTO> add(@Validated @RequestBody LabTestDTO dto) {
        return ResponseEntity.ok(labTestService.addTest(dto));
    }

    // Only ADMIN can update tests
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LabTestDTO> update(@PathVariable Long id, @Validated @RequestBody LabTestDTO dto) {
        return ResponseEntity.ok(labTestService.updateTest(id, dto));
    }

    // Only ADMIN can delete tests
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        labTestService.deleteTest(id);
        return ResponseEntity.ok("Test deleted successfully!");
    }

    // ADMIN and STAFF can view all tests
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<LabTestDTO>> getAll() {
        return ResponseEntity.ok(labTestService.getAllTests());
    }

    // ADMIN and STAFF can view test by ID
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/{id}")
    public ResponseEntity<LabTestDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(labTestService.getTestById(id));
    }
}
