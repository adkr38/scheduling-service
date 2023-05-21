package com.adkr38.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.adkr38.app.models.*;
import com.adkr38.app.repositories.ActivityRepository;


@RestController("/api/activity")
class ActivityController{

  @Autowired
  private ActivityRepository activityRepository;

  @PostMapping("")
  private void saveActivity(Activity activity){
    activityRepository.save(activity);
  }

}
