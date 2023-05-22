package com.adkr38.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.adkr38.app.models.*;
import com.adkr38.app.dtos.*;
import com.adkr38.app.services.impl.UserService;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController{

  @Autowired
  private UserService userService;

  @GetMapping("/validate")
  ResponseEntity<ResponseDTO> getValidation(){
    return ResponseEntity.status(200).body(new SuccessDTO<>(List.of(), "Valid sessionId", 200));
  }

  @GetMapping("/users")
  ResponseEntity<ResponseDTO> getUsers(){
    return ResponseEntity.status(200).body(new SuccessDTO<>(userService.getUsers(), "success", 200));
  }


  @PostMapping("/users")
  ResponseEntity<ResponseDTO> saveUser(@RequestBody User user){
    User savedUser = userService.saveUser(user);
    userService.addRoleToUser(savedUser.getUsername(), "ROLE_USER");
    return ResponseEntity.status(201).body(new SuccessDTO<>(List.of(savedUser), "User created & given ROLE_USER", 201));
  }


}
