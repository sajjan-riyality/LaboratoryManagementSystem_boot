package com.lab.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.dto.AppointmentDTO;
import com.lab.entity.Appointment;
import com.lab.repository.AppointmentRepository;
import com.lab.service.AppointmentService;
import com.lab.util.MapperUtil;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public AppointmentDTO addAppointment(AppointmentDTO dto) {
        Appointment appointment = mapperUtil.toEntity(dto, Appointment.class);
        Appointment saved = appointmentRepository.save(appointment);
        return mapperUtil.toDTO(saved, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Update fields
        appointment.setPatientId(dto.getPatientId());
        appointment.setStaffId(dto.getStaffId());
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus(dto.getStatus());
        appointment.setRemarks(dto.getRemarks());

        Appointment updated = appointmentRepository.save(appointment);
        return mapperUtil.toDTO(updated, AppointmentDTO.class);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return mapperUtil.toDTO(appointment, AppointmentDTO.class);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(a -> mapperUtil.toDTO(a, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(a -> mapperUtil.toDTO(a, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getByStaff(Long staffId) {
        return appointmentRepository.findByStaffId(staffId)
                .stream()
                .map(a -> mapperUtil.toDTO(a, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return appointmentRepository.findByAppointmentDate(localDate)
                .stream()
                .map(a -> mapperUtil.toDTO(a, AppointmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getByStatus(String status) {
        return appointmentRepository.findByStatus(status)
                .stream()
                .map(a -> mapperUtil.toDTO(a, AppointmentDTO.class))
                .collect(Collectors.toList());
    }
}
