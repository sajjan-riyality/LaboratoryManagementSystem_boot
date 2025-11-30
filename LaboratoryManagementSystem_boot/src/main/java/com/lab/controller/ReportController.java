package com.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lab.dto.ReportDTO;
import com.lab.service.ReportService;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Only ADMIN and STAFF can add reports
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<ReportDTO> add(@Validated @RequestBody ReportDTO dto) {
        return ResponseEntity.ok(reportService.addReport(dto));
    }

    // Only ADMIN and STAFF can update reports
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<ReportDTO> update(@PathVariable Long id, @Validated @RequestBody ReportDTO dto) {
        return ResponseEntity.ok(reportService.updateReport(id, dto));
    }

    // Only ADMIN and STAFF can delete reports
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok("Report deleted successfully!");
    }

    // ADMIN and STAFF can view all reports
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<ReportDTO>> getAll() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    // ADMIN, STAFF, PATIENT (own reports only) can view by ID
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF','ROLE_PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reportService.getReportById(id));
    }

    // ADMIN, STAFF, PATIENT (own reports only) can view reports by patient
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF','ROLE_PATIENT')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<ReportDTO>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(reportService.getReportsByPatientId(patientId));
    }

    // Only ADMIN and STAFF can view reports by test
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/test/{testId}")
    public ResponseEntity<List<ReportDTO>> getByTest(@PathVariable Long testId) {
        return ResponseEntity.ok(reportService.getReportsByTestId(testId));
    }
}
