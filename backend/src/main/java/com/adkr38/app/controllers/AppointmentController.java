package com.adkr38.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.adkr38.app.models.*;
import com.adkr38.app.dtos.*;
import com.adkr38.app.services.impl.UserService;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController{

  // @Autowired
  // private UserService userService;
  //
  // @GetMapping("/appointments/all")
  // ResponseEntity<ResponseDTO> getAllAppointments(){
  //
  // }
  //
  // @GetMapping("/appointments/")
  // ResponseEntity<ResponseDTO> getUserAppointments(@PathVariable User user){
  //   return ResponseEntity.status(200).body(new SuccessDTO<>(user.getAppointments(), "success", 200));
  //
  // }
  //
  // @DeleteMapping("appointments/{}")
  // ResponseEntity<ResponseDTO> getUserAppointments(@PathVariable User user){
  //   return ResponseEntity.status(200).body(new SuccessDTO<>(user.getAppointments(), "success", 200));
  //
  // }

}
