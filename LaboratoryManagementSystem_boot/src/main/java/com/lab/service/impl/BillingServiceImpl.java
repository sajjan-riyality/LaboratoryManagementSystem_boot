package com.lab.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.BillingDTO;
import com.lab.entity.Billing;
import com.lab.repository.BillingRepository;
import com.lab.service.BillingService;
import com.lab.util.MapperUtil;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public BillingDTO createBill(BillingDTO billingDTO) {
        Billing entity = mapperUtil.toEntity(billingDTO, Billing.class);
        Billing saved = billingRepository.save(entity);
        return mapperUtil.toDTO(saved, BillingDTO.class);
    }

    @Override
    public BillingDTO updateBill(Long id, BillingDTO dto) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        // Update fields
        billing.setTotalAmount(dto.getTotalAmount());
        billing.setDiscount(dto.getDiscount());
        billing.setNetAmount(dto.getNetAmount());
        billing.setPaymentStatus(dto.getPaymentStatus());
        billing.setBillingDate(dto.getBillingDate());

        return mapperUtil.toDTO(billingRepository.save(billing), BillingDTO.class);
    }

    @Override
    public void deleteBill(Long id) {
        billingRepository.deleteById(id);
    }

    @Override
    public BillingDTO getBillById(Long id) {
        Billing bill = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        return mapperUtil.toDTO(bill, BillingDTO.class);
    }

    @Override
    public List<BillingDTO> getAllBills() {
        return billingRepository.findAll()
                .stream()
                .map(bill -> mapperUtil.toDTO(bill, BillingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BillingDTO> getBillsByPatient(Long patientId) {
        return billingRepository.findByPatientId(patientId)
                .stream()
                .map(bill -> mapperUtil.toDTO(bill, BillingDTO.class))
                .collect(Collectors.toList());
    }
}
