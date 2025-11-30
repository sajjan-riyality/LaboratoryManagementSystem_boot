package com.lab.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.LabTestDTO;
import com.lab.entity.LabTest;
import com.lab.repository.LabTestRepository;
import com.lab.service.LabTestService;
import com.lab.util.MapperUtil;

@Service
public class LabTestServiceImpl implements LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public LabTestDTO addTest(LabTestDTO dto) {
        LabTest entity = mapperUtil.toEntity(dto, LabTest.class);
        LabTest saved = labTestRepository.save(entity);
        return mapperUtil.toDTO(saved, LabTestDTO.class);
    }

    @Override
    public LabTestDTO updateTest(Long id, LabTestDTO dto) {
        LabTest existing = labTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        existing.setTestName(dto.getTestName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setCategory(dto.getCategory());

        return mapperUtil.toDTO(labTestRepository.save(existing), LabTestDTO.class);
    }

    @Override
    public void deleteTest(Long id) {
        labTestRepository.deleteById(id);
    }

    @Override
    public LabTestDTO getTestById(Long id) {
        LabTest labTest = labTestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        return mapperUtil.toDTO(labTest, LabTestDTO.class);
    }

    @Override
    public List<LabTestDTO> getAllTests() {
        return labTestRepository.findAll()
                .stream()
                .map(test -> mapperUtil.toDTO(test, LabTestDTO.class))
                .collect(Collectors.toList());
    }
}
