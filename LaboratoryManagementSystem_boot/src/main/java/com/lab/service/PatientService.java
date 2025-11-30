package com.lab.service;

import java.util.List;

import com.lab.dto.PatientDTO;

public interface PatientService {

    PatientDTO addPatient(PatientDTO patientDTO);

    PatientDTO updatePatient(Long id, PatientDTO patientDTO);

    void deletePatient(Long id);

    PatientDTO getPatientById(Long id);

    List<PatientDTO> getAllPatients();
}
