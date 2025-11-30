package com.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.LabTest;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {

}
