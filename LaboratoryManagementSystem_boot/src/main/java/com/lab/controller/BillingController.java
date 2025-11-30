package com.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lab.dto.BillingDTO;
import com.lab.service.BillingService;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin(origins = "*")
public class BillingController {

    @Autowired
    private BillingService billingService;

    // Only ADMIN can create bills
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<BillingDTO> create(@Validated @RequestBody BillingDTO billingDTO) {
        return ResponseEntity.ok(billingService.createBill(billingDTO));
    }

    // Only ADMIN can update bills
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<BillingDTO> update(@PathVariable Long id, @Validated @RequestBody BillingDTO billingDTO) {
        return ResponseEntity.ok(billingService.updateBill(id, billingDTO));
    }

    // Only ADMIN can delete bills
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        billingService.deleteBill(id);
        return ResponseEntity.ok("Bill deleted successfully!");
    }

    // ADMIN and STAFF can view all bills
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<BillingDTO>> getAll() {
        return ResponseEntity.ok(billingService.getAllBills());
    }

    // ADMIN, STAFF, and PATIENT (own bills only) can view bill by ID
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF','ROLE_PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<BillingDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(billingService.getBillById(id));
    }

    // ADMIN, STAFF, and PATIENT (own bills only)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF','ROLE_PATIENT')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<BillingDTO>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(billingService.getBillsByPatient(patientId));
    }
}
