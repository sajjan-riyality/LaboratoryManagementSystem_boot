package com.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lab.dto.PatientDTO;
import com.lab.service.PatientService;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Only ADMIN can add patients
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PatientDTO> add(@Validated @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.addPatient(patientDTO));
    }

    // Only ADMIN can update patient
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> update(@PathVariable Long id, @Validated @RequestBody PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientDTO));
    }

    // Only ADMIN can delete patient
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully!");
    }

    // ADMIN and STAFF can view all patients
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAll() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // ADMIN, STAFF, and PATIENT (own data only) can view patient by ID
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF','ROLE_PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
}
