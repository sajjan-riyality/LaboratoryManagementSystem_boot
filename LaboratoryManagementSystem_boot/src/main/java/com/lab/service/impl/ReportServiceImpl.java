package com.lab.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.ReportDTO;
import com.lab.entity.LabTest;
import com.lab.entity.Patient;
import com.lab.entity.Report;
import com.lab.repository.LabTestRepository;
import com.lab.repository.PatientRepository;
import com.lab.repository.ReportRepository;
import com.lab.service.ReportService;
import com.lab.util.MapperUtil;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private MapperUtil mapper;

    @Override
    public ReportDTO addReport(ReportDTO dto) {

        Report report = mapper.toEntity(dto, Report.class);

        // Handle nested objects manually
        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            report.setPatient(patient);
        }

        if (dto.getTestId() != null) {
            LabTest test = labTestRepository.findById(dto.getTestId())
                    .orElseThrow(() -> new RuntimeException("Test not found"));
            report.setLabTest(test);
        }

        if (report.getReportDate() == null)
            report.setReportDate(LocalDate.now());

        return mapper.toDTO(reportRepository.save(report), ReportDTO.class);
    }

    @Override
    public ReportDTO updateReport(Long id, ReportDTO dto) {

        Report existing = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        mapper.toEntity(dto, Report.class);   // maps only simple fields

        // Update relations manually
        if (dto.getPatientId() != null) {
            Patient patient = patientRepository.findById(dto.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            existing.setPatient(patient);
        }

        if (dto.getTestId() != null) {
            LabTest test = labTestRepository.findById(dto.getTestId())
                    .orElseThrow(() -> new RuntimeException("Lab test not found"));
            existing.setLabTest(test);
        }

        if (dto.getResults() != null) existing.setResults(dto.getResults());
        if (dto.getRemarks() != null) existing.setRemarks(dto.getRemarks());
        if (dto.getReportDate() != null) existing.setReportDate(dto.getReportDate());
        if (dto.getFilePath() != null) existing.setFilePath(dto.getFilePath());

        return mapper.toDTO(reportRepository.save(existing), ReportDTO.class);
    }

    @Override
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    @Override
    public ReportDTO getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        return mapper.toDTO(report, ReportDTO.class);
    }

    @Override
    public List<ReportDTO> getAllReports() {
        return reportRepository.findAll()
                .stream()
                .map(r -> mapper.toDTO(r, ReportDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportDTO> getReportsByPatientId(Long patientId) {
        return reportRepository.findByPatientId(patientId)
                .stream()
                .map(r -> mapper.toDTO(r, ReportDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportDTO> getReportsByTestId(Long testId) {
        return reportRepository.findByLabTestId(testId)
                .stream()
                .map(r -> mapper.toDTO(r, ReportDTO.class))
                .collect(Collectors.toList());
    }
}
