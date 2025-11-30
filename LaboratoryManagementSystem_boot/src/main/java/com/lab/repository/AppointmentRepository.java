package com.lab.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByStaffId(Long staffId);

    List<Appointment> findByAppointmentDate(LocalDate date);

    List<Appointment> findByStatus(String status);
}
