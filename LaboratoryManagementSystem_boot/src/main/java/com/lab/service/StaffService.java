package com.lab.service;

import java.util.List;

import com.lab.dto.StaffDTO;

public interface StaffService {

    StaffDTO addStaff(StaffDTO staffDTO);

    StaffDTO updateStaff(Long id, StaffDTO staffDTO);

    void deleteStaff(Long id);

    StaffDTO getStaffById(Long id);

    List<StaffDTO> getAllStaff();

    List<StaffDTO> getByRole(String role);

    List<StaffDTO> getByStatus(String status);
}
