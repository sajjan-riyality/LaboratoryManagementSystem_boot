package com.lab.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.PatientDTO;
import com.lab.entity.Patient;
import com.lab.repository.PatientRepository;
import com.lab.service.PatientService;
import com.lab.util.MapperUtil;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public PatientDTO addPatient(PatientDTO patientDTO) {
        Patient patient = mapperUtil.toEntity(patientDTO, Patient.class);
        Patient saved = patientRepository.save(patient);
        return mapperUtil.toDTO(saved, PatientDTO.class);
    }

    @Override
    public PatientDTO updatePatient(Long id, PatientDTO patientDTO) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // update values
        existing.setFullName(patientDTO.getFullName());
        existing.setAge(patientDTO.getAge());
        existing.setGender(patientDTO.getGender());
        existing.setPhone(patientDTO.getPhone());
        existing.setEmail(patientDTO.getEmail());
        existing.setAddress(patientDTO.getAddress());

        Patient updated = patientRepository.save(existing);
        return mapperUtil.toDTO(updated, PatientDTO.class);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return mapperUtil.toDTO(patient, PatientDTO.class);
    }

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patient -> mapperUtil.toDTO(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }
}
