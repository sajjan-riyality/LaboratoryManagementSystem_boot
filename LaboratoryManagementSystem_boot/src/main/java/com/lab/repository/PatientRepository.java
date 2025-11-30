package com.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
