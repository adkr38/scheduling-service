package com.adkr38.app.services.impl;
import com.adkr38.app.models.*;
import com.adkr38.app.repositories.*;
import com.adkr38.app.dtos.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService{
  @Autowired
  private ActivityRepository activityRepository;

  public int saveActivity(Activity activity){
    Optional<Activity> optionalActivity = activityRepository.findByActivity(activity.getActivity());
    if (optionalActivity.isPresent()){
      return 0;
    }

    activityRepository.save(activity);
    return 1;

  }

  public int updateActivity(Activity activity){
    Optional<Activity> optionalActivity = activityRepository.findByActivity(activity.getActivity());
    if (optionalActivity.isEmpty()){
      activityRepository.save(activity);
      return 1;
    }
    activity.setId(optionalActivity.get().getId());
    activityRepository.save(activity);
    return 0;

  }

}
