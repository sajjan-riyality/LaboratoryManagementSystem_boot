package com.lab.service;

import java.util.List;

import com.lab.dto.BillingDTO;

public interface BillingService {

    BillingDTO createBill(BillingDTO billingDTO);

    BillingDTO updateBill(Long id, BillingDTO billingDTO);

    void deleteBill(Long id);

    BillingDTO getBillById(Long id);

    List<BillingDTO> getAllBills();

    List<BillingDTO> getBillsByPatient(Long patientId);
}
