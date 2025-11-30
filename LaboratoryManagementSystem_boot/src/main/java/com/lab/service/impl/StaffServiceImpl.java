package com.lab.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.StaffDTO;
import com.lab.entity.Staff;
import com.lab.repository.StaffRepository;
import com.lab.service.StaffService;
import com.lab.util.MapperUtil;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private MapperUtil mapper;

    @Override
    public StaffDTO addStaff(StaffDTO dto) {
        Staff staff = mapper.toEntity(dto, Staff.class);
        return mapper.toDTO(staffRepository.save(staff), StaffDTO.class);
    }

    @Override
    public StaffDTO updateStaff(Long id, StaffDTO dto) {

        Staff existing = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        // map simple fields
        Staff mapped = mapper.toEntity(dto, Staff.class);

        existing.setName(mapped.getName());
        existing.setRole(mapped.getRole());
        existing.setEmail(mapped.getEmail());
        existing.setPhone(mapped.getPhone());
        existing.setJoiningDate(mapped.getJoiningDate());
        existing.setLeavingDate(mapped.getLeavingDate());
        existing.setStatus(mapped.getStatus());

        return mapper.toDTO(staffRepository.save(existing), StaffDTO.class);
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public StaffDTO getStaffById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        return mapper.toDTO(staff, StaffDTO.class);
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(s -> mapper.toDTO(s, StaffDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffDTO> getByRole(String role) {
        return staffRepository.findByRole(role)
                .stream()
                .map(s -> mapper.toDTO(s, StaffDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffDTO> getByStatus(String status) {
        return staffRepository.findByStatus(status)
                .stream()
                .map(s -> mapper.toDTO(s, StaffDTO.class))
                .collect(Collectors.toList());
    }
}
