package com.adkr38.app.services.impl;
import com.adkr38.app.models.*;
import com.adkr38.app.repositories.*;
import com.adkr38.app.dtos.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AppointmentService{
  @Autowired
  private AppointmentRepository appointmentRepository;

  public int saveAppointment(Appointment appointment){
    Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointment.getId());
    if (optionalAppointment.isPresent()){
      return 0;
    }

    appointmentRepository.save(appointment);
    return 1;

  }

  public int updateAppointment(Appointment appointment){
    Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointment.getId());
    if (optionalAppointment.isEmpty()){
      appointmentRepository.save(appointment);
      return 1;
    }
    appointment.setId(optionalAppointment.get().getId());
    appointmentRepository.save(appointment);
    return 0;

  }

  public int deleteAppointment(Appointment appointment){
    Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointment.getId());
    if (optionalAppointment.isEmpty()){
      return 0;
    }
    appointmentRepository.deleteById(appointment.getId());
    return 1;
  }

}
