package com.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByPatientId(Long patientId);
    List<Report> findByLabTestId(Long labTestId);
}
