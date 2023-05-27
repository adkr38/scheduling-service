package com.adkr38.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.adkr38.app.dtos.*;
import com.adkr38.app.models.*;
import com.adkr38.app.repositories.ActivityRepository;
import com.adkr38.app.repositories.AppointmentRepository;
import com.adkr38.app.repositories.UserRepository;
import com.adkr38.app.services.impl.AppointmentService;
import com.adkr38.app.services.impl.UserService;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/appointment")
class AppointmentController{

  @Autowired
  private AppointmentService appointmentService;

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private ActivityRepository activityRepository;

  @Autowired
  private UserService userService;


  @GetMapping("/all")
  private ResponseEntity<ResponseDTO> getAppointments(){

    return ResponseEntity.status(200).body(new SuccessDTO<>(appointmentRepository.findAll(), "success", 200));

  }

  @PostMapping("")
  private ResponseEntity<ResponseDTO> saveAppointment(@RequestBody AppointmentDTO appointmentDTO){
    
    int savedAppointment = userService.addAppointment(appointmentDTO);

    switch (savedAppointment){
      case 0:
        return ResponseEntity.status(409).body(new ErrorDTO("Ya existe una cita a esta hora", 409));
      case 400:
        return ResponseEntity.status(400).body(new ErrorDTO("Formato de tiempo inadecuado", 400));

      case 404:
        return ResponseEntity.status(400).body(new ErrorDTO("La fecha seleccionada ya ha pasado", 400));

      case 409:
        return ResponseEntity.status(409).body(new ErrorDTO("Ya existe una cita a esta hora y fecha", 409));
    }

    return ResponseEntity.status(201).body(new SuccessDTO<>(List.of(), "Cita creada!", 201));

  }

  @PutMapping("/update")
  private ResponseEntity<ResponseDTO> updateAppointment(Appointment appointment){
    int updatedAppointment = appointmentService.saveAppointment(appointment);

    if (updatedAppointment == 0){
      return ResponseEntity.status(201).body(new ErrorDTO("Appointment created", 201));
    }

    return ResponseEntity.noContent().build();

  }

  @DeleteMapping("/delete")
  private ResponseEntity<ResponseDTO> deleteAppointment(Appointment appointment){
    int deletedAppointment = appointmentService.deleteAppointment(appointment);

    if (deletedAppointment == 0){
      return ResponseEntity.status(404).body(new ErrorDTO("Appointment didn't exist", 404));
    }

    return ResponseEntity.noContent().build();

  }

  @GetMapping("/byUser/{}")
  private ResponseEntity<ResponseDTO> findByUser(@PathVariable String username){
    return ResponseEntity.status(200).body(new SuccessDTO<>(appointmentRepository.findByUserUsername(username), "success", 200));


  }


}
