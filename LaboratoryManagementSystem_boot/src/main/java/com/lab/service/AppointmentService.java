package com.lab.service;

import java.util.List;

import com.lab.dto.AppointmentDTO;

public interface AppointmentService {

    AppointmentDTO addAppointment(AppointmentDTO dto);

    AppointmentDTO updateAppointment(Long id, AppointmentDTO dto);

    void deleteAppointment(Long id);

    AppointmentDTO getAppointmentById(Long id);

    List<AppointmentDTO> getAllAppointments();

    List<AppointmentDTO> getByPatient(Long patientId);

    List<AppointmentDTO> getByStaff(Long staffId);

    List<AppointmentDTO> getByDate(String date);

    List<AppointmentDTO> getByStatus(String status);
}
