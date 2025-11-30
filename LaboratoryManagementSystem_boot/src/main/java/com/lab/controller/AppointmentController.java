package com.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lab.dto.AppointmentDTO;
import com.lab.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // ADMIN and STAFF can create appointments
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @PostMapping
    public ResponseEntity<AppointmentDTO> add(@Validated @RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentService.addAppointment(dto));
    }

    // ADMIN and STAFF can update appointments
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDTO> update(@PathVariable Long id, @Validated @RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, dto));
    }

    // ADMIN and STAFF can delete appointments
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted successfully");
    }

    // ADMIN, STAFF, PATIENT can view appointment by ID
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF','ROLE_PATIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    // ADMIN and STAFF can view all appointments
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> getAll() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // ADMIN, STAFF, PATIENT can view appointments by patient
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF','ROLE_PATIENT')")
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentDTO>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getByPatient(patientId));
    }

    // ADMIN and STAFF can view appointments by staff
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<AppointmentDTO>> getByStaff(@PathVariable Long staffId) {
        return ResponseEntity.ok(appointmentService.getByStaff(staffId));
    }

    // ADMIN and STAFF can view appointments by date
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<AppointmentDTO>> getByDate(@PathVariable String date) {
        return ResponseEntity.ok(appointmentService.getByDate(date));
    }

    // ADMIN and STAFF can filter by status
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STAFF')")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<AppointmentDTO>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(appointmentService.getByStatus(status));
    }
}
