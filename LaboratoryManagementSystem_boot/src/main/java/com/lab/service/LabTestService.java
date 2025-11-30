package com.lab.service;

import java.util.List;

import com.lab.dto.LabTestDTO;

public interface LabTestService {

    LabTestDTO addTest(LabTestDTO dto);

    LabTestDTO updateTest(Long id, LabTestDTO dto);

    void deleteTest(Long id);

    LabTestDTO getTestById(Long id);

    List<LabTestDTO> getAllTests();
}
