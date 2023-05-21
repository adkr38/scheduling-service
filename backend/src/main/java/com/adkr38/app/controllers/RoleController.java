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
public class RoleController{

  @Autowired
  private UserService userService;

  @PostMapping("/roles")
  public ResponseEntity<ResponseDTO> saveRole(@RequestBody Role role){
    Role savedRole = userService.saveRole(role);
    // if (savedRole == 0){
    //   return ResponseEntity.status(409).body(new ErrorDTO("Conflict: Role already exists.", 409));
    // }
    return ResponseEntity.status(201).body(new SuccessDTO<>(List.of(role), "success", 201));
  }

  @PostMapping("/roles/add")
  public ResponseEntity<ResponseDTO> saveRole(User user, Role role){
    userService.addRoleToUser(user.getUsername(), role.getName());
    return ResponseEntity.status(200).body(new SuccessDTO<>(List.of(), String.format("Role %s added to user %s", role.getName(),user.getUsername()), 200));
  }

}
