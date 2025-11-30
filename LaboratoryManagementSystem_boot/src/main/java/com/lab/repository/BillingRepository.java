package com.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long> {

    List<Billing> findByPatientId(Long patientId);
}
