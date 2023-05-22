package com.adkr38.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.adkr38.app.dtos.*;
import com.adkr38.app.models.*;
import com.adkr38.app.repositories.ActivityRepository;
import com.adkr38.app.services.impl.ActivityService;
import java.util.List;


@RestController
@RequestMapping("/api/activity")
class ActivityController{

  @Autowired
  private ActivityService activityService;
  @Autowired
  private ActivityRepository activityRepository;

  @GetMapping("/all")
  private ResponseEntity<ResponseDTO> getActivities(){

    return ResponseEntity.status(200).body(new SuccessDTO<>(activityRepository.findAll(), "success", 200));

  }

  @PostMapping("")
  private ResponseEntity<ResponseDTO> saveActivity(Activity activity){
    int savedActivity = activityService.saveActivity(activity);

    if (savedActivity == 0){
      return ResponseEntity.status(409).body(new ErrorDTO("Activity already exists", 409));
    }

    return ResponseEntity.status(201).body(new SuccessDTO<>(List.of(), "Activity created", 201));

  }

}
