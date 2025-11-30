package com.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findByRole(String role);

    List<Staff> findByStatus(String status);
}
