package com.adkr38.app.services.impl;
import com.adkr38.app.models.*;
import com.adkr38.app.repositories.*;
import com.adkr38.app.dtos.*;
import java.util.List;
import java.util.Optional;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService{
  @Autowired
  private AppointmentRepository appointmentRepository;

  public int saveAppointment(Appointment appointment){
    if (appointment.getDate().getMinute() != 0 && appointment.getDate().getMinute() != 30){
      return 400;
    }

    if (appointment.getDate().isBefore(LocalDateTime.now())){
      return 404;
    }

    List<Appointment> currentAppointments = appointmentRepository.findAll();

    List<Appointment> filteredAppointments = currentAppointments.stream()
        .filter(x -> {
            LocalDateTime inputDateTime = appointment.getDate();
            LocalDateTime otherDateTime = x.getDate();
            long minutesDifference = ChronoUnit.MINUTES.between(inputDateTime, otherDateTime);
            return Math.abs(minutesDifference) < 30;
        })
        .collect(Collectors.toList());



    if (filteredAppointments.size() > 0){
      return 409;
    }

    if (appointment.getId() != null){
      Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointment.getId());
      if (optionalAppointment.isPresent()){
        return 0;
      }
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
