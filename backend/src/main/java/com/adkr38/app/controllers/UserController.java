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
@CrossOrigin(origins = "http://localhost:5173")
public class UserController{

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  ResponseEntity<ResponseDTO> getUsers(){
    return ResponseEntity.status(200).body(new SuccessDTO<>(userService.getUsers(), "success", 200));
  }


  @PostMapping("/users")
  ResponseEntity<ResponseDTO> saveUser(User user){
    User savedUser = userService.saveUser(user);

    return ResponseEntity.status(201).body(new SuccessDTO<>(List.of(savedUser), "user created", 201));
  }


}
