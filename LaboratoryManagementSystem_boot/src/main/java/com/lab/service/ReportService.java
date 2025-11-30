package com.lab.service;

import java.util.List;

import com.lab.dto.ReportDTO;

public interface ReportService {

    ReportDTO addReport(ReportDTO dto);

    ReportDTO updateReport(Long id, ReportDTO dto);

    void deleteReport(Long id);

    ReportDTO getReportById(Long id);

    List<ReportDTO> getAllReports();

    List<ReportDTO> getReportsByPatientId(Long patientId);

    List<ReportDTO> getReportsByTestId(Long testId);
}
